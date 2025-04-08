package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {

    private static Connection defaultConnection = null;
    private static final Map<String, Connection> connections = new HashMap<>();

    public static void connect(String dbPath) {
        try {
            String url = "jdbc:sqlite:" + dbPath;
            defaultConnection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Nie udało się połączyć z bazą danych: " + dbPath, e);
        }
    }

    public static void connect(String dbPath, String key) {
        try {
            String url = "jdbc:sqlite:" + dbPath;
            Connection conn = DriverManager.getConnection(url);
            connections.put(key, conn);
        } catch (SQLException e) {
            throw new RuntimeException("Nie udało się połączyć z bazą danych: " + dbPath, e);
        }
    }

    public static Connection getConnection() {
        if (defaultConnection == null) {
            throw new IllegalStateException("Domyślne połączenie nie jest zainicjowane. Użyj metody connect(dbPath).");
        }
        return defaultConnection;
    }
    public static Connection getConnection(String key) {
        Connection conn = connections.get(key);
        if (conn == null) {
            throw new IllegalStateException("Połączenie z kluczem '" + key + "' nie jest zainicjowane. " +
                    "Użyj najpierw metody connect(dbPath, \"" + key + "\").");
        }
        return conn;
    }

    public static void disconnect() {
        if (defaultConnection != null) {
            try {
                defaultConnection.close();
                defaultConnection = null;
            } catch (SQLException e) {
                throw new RuntimeException("Błąd podczas zamykania domyślnego połączenia.", e);
            }
        }
    }

    public static void disconnect(String key) {
        if (key == null || key.isEmpty()) {
            disconnect();
            return;
        }
        Connection conn = connections.get(key);
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Błąd podczas zamykania połączenia o kluczu: " + key, e);
            }
            connections.remove(key);
        }
    }
}