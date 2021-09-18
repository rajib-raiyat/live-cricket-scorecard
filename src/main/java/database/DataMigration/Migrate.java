package database.DataMigration;

import database.DatabaseConnection;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static database.DatabaseConfig.DB_NAME;
import static database.DatabaseConfig.DB_TABLE_NAME;

public class Migrate {

    static boolean isNumeric(String str) {
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

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert statement != null;

        String table_name = DB_TABLE_NAME;

        try (BufferedReader br = Files.newBufferedReader(Paths.get("dataset/Matches IPL 2008-2019.csv"))) {
            String[] first_line = br.readLine().split(",");
            String[] second_line = br.readLine().split(",", -1);
            StringBuilder sql_query_ct = new StringBuilder();
            StringBuilder sql_query_cd = new StringBuilder();

            sql_query_ct.append("CREATE TABLE `").append(DB_NAME).append("`.`").append(table_name).append("` (");

            for (int i = 0; i < first_line.length; i++) {
                first_line[i] = first_line[i].replace("\"", "");
                second_line[i] = second_line[i].replace("\"", "");

                if (isNumeric(second_line[i]) && !Objects.equals(first_line[i], "season")) {
                    db_data_type = "INTEGER";
//                } else if (Objects.equals(first_line[i], "date") || Objects.equals(first_line[i], "season")) {
                } else if (Objects.equals(first_line[i], "date")) {
                    db_data_type = "DATE";
                } else {
                    db_data_type = "VARCHAR(600)";
                }

                if (Objects.equals(first_line[i], "id")) {
                    first_line[i] = "match_id";
                }

                sql_query_ct.append("`").append(first_line[i]).append("`").append(" ").append(db_data_type).append(", ");
            }

            sql_query_ct.append(" `live_match` BOOLEAN NOT NULL DEFAULT false,").append(" PRIMARY KEY (`match_id`, `live_match`))").append(" ENGINE = InnoDB;");

            if (statement.executeUpdate("SHOW TABLES LIKE '" + table_name + "';") == 0) {
                statement.executeUpdate(String.valueOf(sql_query_ct));
                System.out.println("`" + table_name + "` table created successfully.");
            }
            System.out.println("Inserting data into `" + table_name + "` table and search index.");

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
                StringToDate(we, s);
            }

            sql_query_cd.append(we).append(")");

            ResultSet rs = statement.executeQuery("SELECT * FROM " + table_name + " WHERE match_id=" + second_line[0] + ";");
            if (!rs.next()) {
                statement.executeUpdate(String.valueOf(sql_query_cd).replace(",)", ")") + ";");
            }

            we = new StringBuilder();
            sql_query_cd = new StringBuilder(old);

            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "").replace(", ", "-").replace('\'', '`');

                for (String s : line.split(",", -1)) {
                    StringToDate(we, s);
                }

                sql_query_cd.append(we.append(");"));
                ResultSet rs1 = statement.executeQuery("SELECT * FROM " + table_name + " WHERE match_id=" + line.split(",")[0] + ";");

                if (!rs1.next()) {
                    SearchMigration.bulk_index(first_line, line);
                    statement.executeUpdate(String.valueOf(sql_query_cd).replace(",);", ");"));
                }
                we = new StringBuilder();
                sql_query_cd = new StringBuilder(old);
            }
            System.out.println("Execution successful.");
        }
        MigrateDeliveries.main();
    }

    static void StringToDate(StringBuilder we, String s) {
        if (s.matches("\\d{2}-\\d{2}-\\d{4}")) {
            s = "STR_TO_DATE('" + s + "', '%d-%m-%Y')";
            we.append(s).append(",");
        }
//        else if (s.matches("\\d{4}")) {
//            s = "STR_TO_DATE('" + s + "', '%Y')";
//            we.append(s).append(",");
//        }
        else {
            we.append("'").append(s).append("',");
        }
    }
}
