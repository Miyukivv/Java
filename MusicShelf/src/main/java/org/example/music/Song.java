package org.example.music;

import org.example.database.DatabaseConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public record Song(String artist, String title, int timeInSecond) {

    public static class Persistence {

        public static void init() throws SQLException {
            String sql = """
                CREATE TABLE IF NOT EXISTS song(
                    id INTEGER PRIMARY KEY,
                    artist TEXT NOT NULL,
                    title TEXT NOT NULL,
                    length INTEGER NOT NULL
                )
                """;
            try (PreparedStatement statement = DatabaseConnection.getConnection("songs").prepareStatement(sql)) {
                statement.executeUpdate();
            }

            if (isSongTableEmpty()) {
                loadSongsFromCSV("src/main/resources/songs.csv");
            }
        }

        public static Optional<Song> read(int id) throws SQLException {
            String sql = "SELECT artist, title, length FROM song WHERE id = ?";
            PreparedStatement statement = DatabaseConnection.getConnection("songs").prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new Song(
                        resultSet.getString("artist"),
                        resultSet.getString("title"),
                        resultSet.getInt("length")
                ));
            }
            return Optional.empty();
        }

        private static boolean isSongTableEmpty() throws SQLException {
            String sql = "SELECT COUNT(*) FROM song";
            try (PreparedStatement statement = DatabaseConnection.getConnection("songs").prepareStatement(sql);
                 ResultSet rs = statement.executeQuery()) {
                return rs.next() && rs.getInt(1) == 0;
            }
        }

        private static void loadSongsFromCSV(String csvPath) {
            String insertSql = "INSERT INTO song(id, artist, title, length) VALUES (?, ?, ?, ?)";
            try (BufferedReader br = new BufferedReader(new FileReader(csvPath));
                 PreparedStatement statement = DatabaseConnection.getConnection("songs").prepareStatement(insertSql)) {

                br.readLine();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");

                    if (parts.length < 4) continue;

                    int id = Integer.parseInt(parts[0].trim());
                    String artist = parts[1].trim();
                    String title = parts[2].trim();
                    int length = Integer.parseInt(parts[3].trim());

                    statement.setInt(1, id);
                    statement.setString(2, artist);
                    statement.setString(3, title);
                    statement.setInt(4, length);
                    statement.executeUpdate();
                }

                System.out.println("<Utwory załadowane z CSV do bazy danych>");
            } catch (Exception e) {
                System.out.println("<Błąd ładowania danych z CSV: " + e.getMessage() + ">");
            }
        }
    }
}
