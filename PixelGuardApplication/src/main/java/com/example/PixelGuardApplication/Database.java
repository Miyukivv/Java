package com.example.PixelGuardApplication;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:pixel.db";

    private Connection conn;
    private Statement stat;
    private static Database instance;

    private Database() {
        connectionOfDatabase();
        createTable();
    }

    private void connectionOfDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Error while opening the connection");
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void createTable() {
        String createMyTable = "CREATE TABLE IF NOT EXISTS entry (token INTEGER NOT NULL, x INTEGER NOT NULL, y INTEGER NOT NULL, color TEXT NOT NULL, timestamp TEXT NOT NULL);";
        try {
            stat.execute(createMyTable);
        } catch (SQLException e) {
            System.err.println("Error while creating the table");
            e.printStackTrace();
        }
    }

    public int removeRecords(int token) {
        String deleteMyRecords = "DELETE FROM entry WHERE token=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(deleteMyRecords);
            preparedStatement.setInt(1, token);

            int records = preparedStatement.executeUpdate();
            return records;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPixelToDatabase(int id, int x, int y, String hexcolor) {
        String insert = "INSERT INTO entry (token, x, y, color, timestamp) VALUES(?, ?, ?, ?, ?);";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement(insert);
            prst.setInt(1, id);
            prst.setInt(2, x);
            prst.setInt(3, y);
            prst.setString(4, hexcolor);
            prst.setString(5, LocalDateTime.now().toString());
            prst.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Pixel> getListOfPixelsFromDatabase() {
        ArrayList<Pixel> pixels = new ArrayList<>();

        try {
            ResultSet result = stat.executeQuery("SELECT * FROM entry");
            int x;
            int y;
            String color;

            while (result.next()) {
                x = result.getInt("x");
                y = result.getInt("y");
                color = result.getString("color");
                pixels.add(new Pixel(x, y, color));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pixels;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error while closing the connection");
            e.printStackTrace();
        }
    }
}
