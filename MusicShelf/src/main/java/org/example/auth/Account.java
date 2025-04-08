package org.example.auth;

import org.example.database.DatabaseConnection;

import javax.naming.AuthenticationException;
import java.sql.*;

public class Account {
    private final int id;
    private final String username;

    public Account(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public static class Persistence {

        public static void init() throws SQLException {
            String sql = """
                CREATE TABLE IF NOT EXISTS account(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL
                )
                """;
            try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
                statement.executeUpdate();
            }
        }
        public static int register(String username, String password) throws SQLException {
            // Najpierw upewniamy się, że tabela 'account' istnieje.
            init();

            String sql = "INSERT INTO account(username, password) VALUES(?, ?)";
            try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();

                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                    throw new SQLException("Nie udało się pobrać wygenerowanego ID użytkownika.");
                }
            }
        }
        public static Account authenticate(String username, String password) throws AuthenticationException {
            try {
                init();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String sql = "SELECT id, username, password FROM account WHERE username = ?";
            try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
                statement.setString(1, username);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        String dbPassword = rs.getString("password");
                        if (password.equals(dbPassword)) {
                            int id = rs.getInt("id");
                            String userFromDb = rs.getString("username");
                            return new Account(id, userFromDb);
                        } else {
                            throw new AuthenticationException("Niepoprawne hasło.");
                        }
                    } else {
                        throw new AuthenticationException("Nie znaleziono użytkownika: " + username);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}