package orgs.clint_pages.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static final String URL = "jdbc:mysql://localhost:3306/tuasl?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "730673145";

    private static Connection connection;

    // Get a connection to the database
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC driver not found.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Close the connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void  con (){
        String url = "jdbc:mysql://localhost:3306/tuasl"; // Replace with your database URL
        String username = "root";                           // Replace with your database username
        String password = "730673145";                           // Replace with your database password

        Connection connection = null; // Initialize connection outside the try block

        try {
            // Load the JDBC driver (optional in newer versions of JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");  // Use 'cj' for MySQL Connector/J 8.0+

            // Establish the connection
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Successfully connected to the database!");
                //  Now you can perform database operations here (e.g., execute queries)
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } finally {
            // Close the connection in the 'finally' block to ensure it's always closed
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }


}
