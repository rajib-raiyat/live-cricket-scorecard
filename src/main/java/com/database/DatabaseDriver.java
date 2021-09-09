package com.database;

import java.sql.SQLException;

public class DatabaseDriver {
    public static void main(String[] args) throws SQLException {

        String query = "INSERT INTO student VALUES ('2018-2-60-22', 'sadat')";
        CRUD.create(query);
    }
}
