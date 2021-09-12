package DataMigration;

import com.database.DatabaseConnection;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
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
        String db_data_type;
        Connection connection = DatabaseConnection.connect();
        Statement statement = null;
        Boolean match_id = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert statement != null;

        System.out.println("Enter the table name: ");
        Scanner sc = new Scanner(System.in);
        String table_name = sc.next();
        sc.nextLine();

        System.out.println("Enter the CSV file path: ");
        String filepath = sc.nextLine();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath))) {
            String[] first_line = br.readLine().split(",");
            String[] second_line = br.readLine().split(",", -1);
            StringBuilder sql_query_ct = new StringBuilder();
            StringBuilder sql_query_cd = new StringBuilder();

            sql_query_ct.append("CREATE TABLE `").append("test").append("`.`").append(table_name).append("` (");

            for (int i = 0; i < first_line.length; i++) {
                first_line[i] = first_line[i].replace("\"", "");
                second_line[i] = second_line[i].replace("\"", "");

                if (isNumeric(second_line[i])) {
                    db_data_type = "INTEGER";
                } else {
                    db_data_type = "VARCHAR(600)";
                }

                if (Objects.equals(first_line[i], "id")) {
                    first_line[i] = "match_id";
                    match_id = true;
                } else {
                    match_id = false;
                }

                sql_query_ct.append("`").append(first_line[i]).append("`").append(" ").append(db_data_type).append(", ");
            }

            sql_query_ct.append(" PRIMARY KEY (`match_id`))").append(" ENGINE = InnoDB;");
            statement.executeUpdate(String.valueOf(sql_query_ct));

            sql_query_cd.append("INSERT INTO `").append(table_name).append("`(");

            for (String s : first_line) {
                sql_query_cd.append("`").append(s).append("`").append(", ");
            }

            sql_query_cd.append(")");
            sql_query_cd = new StringBuilder(String.valueOf(sql_query_cd).replace(", )", ") VALUES ("));
            StringBuilder we = new StringBuilder();

            StringBuilder old = sql_query_cd;
            sql_query_cd = new StringBuilder(String.valueOf(sql_query_cd));


            for (String s : second_line) {
                we.append("'").append(s).append("',");
            }

            sql_query_cd.append(we).append(")");
            statement.executeUpdate(String.valueOf(sql_query_cd).replace(",)", ")") + ";");

            we = new StringBuilder();
            sql_query_cd = new StringBuilder(old);

            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "").replace(", ", "-").replace('\'', '`');
                for (String s : line.split(",", -1)) {
                    we.append("'").append(s).append("',");
                }

                sql_query_cd.append(we.append(");"));
                statement.executeUpdate(String.valueOf(sql_query_cd).replace(",);", ");"));
                we = new StringBuilder();
                sql_query_cd = new StringBuilder(old);
            }
        }
    }
}
