package DataMigration;

import com.database.DatabaseConnection;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class migration {

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        Connection connection = DatabaseConnection.connect();
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert statement != null;

        try (BufferedReader br = Files.newBufferedReader(Paths.get("/home/rajib/Documents/rajib/ewu/Summer 2021/cse110/live-cricket-scorecard/dataset/archive/IPL Matches 2008-2020.csv"))) {
            String[] first_line = br.readLine().split(",");
            String[] second_line = br.readLine().split(",");
            StringBuilder sql_query = new StringBuilder();

            System.out.println("Enter the table name: ");
            Scanner sc = new Scanner(System.in);
            String table_name = sc.next();

            sql_query.append("CREATE TABLE `+ ").append(table_name).append("` (");

            for (int i = 0; i < first_line.length; i++) {
                String db_data_type;

                first_line[i] = first_line[i].replace("\"", "");
                second_line[i] = second_line[i].replace("\"", "");

                if (isNumeric(second_line[i])) {
                    db_data_type = "INTEGER";
                } else {
                    db_data_type = "VARCHAR(600)";
                }

                sql_query.append("`").append(first_line[i]).append("`").append(" ").append(db_data_type).append(", ");
            }
            sql_query.append(")");
            sql_query = new StringBuilder(String.valueOf(sql_query).replace(", )", ");"));

            statement.executeUpdate(String.valueOf(sql_query));
        }
    }
}
