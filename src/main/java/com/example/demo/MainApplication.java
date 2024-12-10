package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApplication {
    private static final String URL = "jdbc:postgresql://localhost:5432/mbs";
    private static final String USERNAME = "project";
    private static final String PASSWORD = "project2";
    // referenced https://mkyong.com/jdbc/how-do-connect-to-postgresql-with-jdbc-driver-java/ to establish connection
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
