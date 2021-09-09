package com.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {


    private static final Connection connection = DatabaseConnection.connect();
    private static Statement statement = null;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void create(String query) throws SQLException {
        statement.executeUpdate(query);
        statement.close();
    }

    public static void update() {

    }

    public static void get() {

    }

    public static void delete() {

    }


}
