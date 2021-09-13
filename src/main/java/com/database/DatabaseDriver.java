package com.database;

import java.sql.SQLException;
import java.util.Dictionary;

public class DatabaseDriver {
    public static void main(String[] args) throws SQLException {
        Dictionary<String, String> dataset = CRUD.getMatchDetailsByID("120");

        if (dataset.isEmpty()) {
            System.out.println("data not found.");
        } else {
            System.out.println(dataset);
        }
    }
}
