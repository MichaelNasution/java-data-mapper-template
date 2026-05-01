package util;

import config.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing JDBC database connections.
 */
public class DBConnection {
    private static Connection connection = null;

    /**
     * Gets a database connection using DriverManager.
     *
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Ensure PostgreSQL driver is loaded (optional for modern JDBC but good practice)
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USER,
                    DatabaseConfig.PASSWORD
                );
            } catch (ClassNotFoundException e) {
                throw new SQLException("PostgreSQL JDBC Driver not found", e);
            }
        }
        return connection;
    }

    /**
     * Closes the database connection.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
