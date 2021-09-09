package com.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDriver {
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.connect();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO student VALUES ('2019-2-60-22', 'ishraq')";
        statement.executeUpdate(query);
        statement.close();

    }
}
