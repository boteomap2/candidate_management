package fa.academy.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static Connection CONNECTION;

    private static final String SERVER_ADDRESS = "localhost";
    private static final String DB_NAME = "candidate_management";
    private static final String SERVER_PORT = "1434";

    public static Connection getConnection() {
        return CONNECTION;
    }

    public static void openConnection() {
        if (CONNECTION != null) {
            System.out.println("Connection to DB is already opened");
            return;
        }
        getInstance();
    }

    public static void closeConnection() {
        if (CONNECTION == null) {
            System.out.println("No Connection to DB");
            return;
        }
        try {
            CONNECTION.close();
            CONNECTION = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getInstance() {
        try {
            // Load and Register the Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionUrl =
                "jdbc:sqlserver://" +
                SERVER_ADDRESS +
                ":" +
                SERVER_PORT +
                ";database=" +
                DB_NAME +
                ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

            // Create and return the Connection to DB
            CONNECTION = DriverManager.getConnection(connectionUrl);
            System.out.println("Connect to DB successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
