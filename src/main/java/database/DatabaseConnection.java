package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {
        Connection connection = null;

        try {
            Class.forName(DatabaseConfig.DRIVER);
            connection = DriverManager.getConnection(
                    DatabaseConfig.SERVER_URL + DatabaseConfig.DB_NAME,
                    DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
