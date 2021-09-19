package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static database.DatabaseConfig.DB_TABLE_NAME;

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

    public static Boolean makeMatchLive(String match_id) throws SQLException {
        String query = "UPDATE `" + DB_TABLE_NAME + "` SET `live_match` = '1' " +
                "WHERE `" + DB_TABLE_NAME + "`.`match_id` = " + match_id +
                " AND `" + DB_TABLE_NAME + "`.`live_match` = 0;";

        int r = statement.executeUpdate(query);
        statement.close();
        return r == 1;
    }

    public static Dictionary<String, String> getMatchDetailsByID(String match_id) throws SQLException {
        Dictionary<String, String> dataset = new Hashtable<>();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ipl_matches WHERE match_id=" + match_id + ";");
        return getDataToString(dataset, resultSet);
    }

    public static ArrayList<Dictionary<String, String>> getLiveMatch() throws SQLException {
        ArrayList<Dictionary<String, String>> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `" + DB_TABLE_NAME + "`WHERE" +
                " live_match=true ORDER BY `ipl_matches`.`date` DESC;");
        return getListOfDictionary(resultSet);
    }

    public static ArrayList<Dictionary<String, String>> getMatchList(String count) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM `ipl_matches` ORDER BY `ipl_matches`.`date` DESC LIMIT 0," + count + ";");
        return getListOfDictionary(resultSet);
    }

    private static ArrayList<Dictionary<String, String>> getListOfDictionary(ResultSet resultSet) throws SQLException {
        ResultSetMetaData md = resultSet.getMetaData();
        ArrayList<Dictionary<String, String>> list = new ArrayList<>();

        while (resultSet.next()) {
            Dictionary<String, String> dataset = new Hashtable<>();

            for (int i = 1; i <= md.getColumnCount(); i++) {
                String column_name = md.getColumnName(i);
                String value = resultSet.getString(column_name);
                dataset.put(column_name, value);
            }
            list.add(dataset);
        }

        return list;
    }

    private static Dictionary<String, String> getDataToString(Dictionary<String, String> dataset, ResultSet resultSet) throws SQLException {
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
