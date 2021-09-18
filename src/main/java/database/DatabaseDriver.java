package database;

import java.sql.SQLException;
import java.util.Dictionary;

public class DatabaseDriver {
    public static void main(String[] args) throws SQLException {
        Dictionary<String, String> dataset = CRUD.getMatchDetailsByID("456");

        if (dataset.isEmpty()) {
            System.out.println("data not found.");
        } else {
            System.out.println("0");
            System.out.println(dataset);
        }
        System.out.println("1");
        Dictionary<String, String> dataset1 = CRUD.getLiveMatch();
        System.out.println(dataset1);
        System.out.println("2");
        Dictionary<String, String> dataset2 = CRUD.getMatchList("4");
        System.out.println(dataset2);
    }
}
