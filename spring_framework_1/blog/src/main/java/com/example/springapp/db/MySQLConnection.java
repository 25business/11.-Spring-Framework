package com.example.springapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static Connection connection;

    public static Connection get() throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/spring_blog", "jadmin", "1234");
        }
        return connection;
    }
}
