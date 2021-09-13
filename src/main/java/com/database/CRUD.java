package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;

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

    public static Dictionary<String, String> getMatchDetailsByID(String match_id) throws SQLException {
        Dictionary<String, String> dataset = new Hashtable<>();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ipl_matches WHERE match_id=" + match_id + ";");
        int count = resultSet.getMetaData().getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= count; i++) {
                String column_name = resultSet.getMetaData().getColumnName(i);
                String value = resultSet.getString(column_name);
                dataset.put(column_name, value);
            }
        }

        return dataset;
    }

    public static void delete() {
    }
}
