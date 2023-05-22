package com.digdes.pms.repository.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private final static String URL = "jdbc:postgresql://localhost:5433/postgres";
    private final static String USER = "postgres";
    private final static String PASSWORD = "postgres";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }

        return connection;
    }
}
