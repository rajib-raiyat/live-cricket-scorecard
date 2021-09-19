package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;

public class DatabaseDriver {
    public static void main(String[] args) throws SQLException {
        Dictionary<String, String> dataset = CRUD.getMatchDetailsByID("456");

        if (dataset.isEmpty()) {
            System.out.println("data not found.");
        } else {
            System.out.println("Get a match details.");
            System.out.println(dataset);
        }

        System.out.println();
        System.out.println("Get current list of live matches.");
        ArrayList<Dictionary<String, String>> dataset1 = CRUD.getLiveMatch();
        System.out.println(dataset1);

        System.out.println();
        System.out.println("Get current list of latest matches.");
        ArrayList<Dictionary<String, String>> dataset2 = CRUD.getMatchList("4");
        System.out.println(dataset2);

        System.out.println();
        System.out.println("Make a match live.");
        Boolean r = CRUD.makeMatchLive("3");

        if (r) {
            System.out.println("Update done.");
        } else {
            System.out.println("Data not found or already a live match.");
        }
    }
}
