package util;

import config.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing JDBC database connections.
 * Implements basic connection management and error reporting.
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
                // Ensure PostgreSQL driver is loaded
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USER,
                    DatabaseConfig.PASSWORD
                );
            } catch (ClassNotFoundException e) {
                System.err.println("[DB ERROR] PostgreSQL JDBC Driver not found in classpath.");
                throw new SQLException("PostgreSQL JDBC Driver missing", e);
            } catch (SQLException e) {
                System.err.println("[DB ERROR] Failed to connect to database at: " + DatabaseConfig.URL);
                throw e;
            }
        }
        return connection;
    }

    /**
     * Closes the database connection gracefully.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("[DB] Connection closed successfully.");
            } catch (SQLException e) {
                System.err.println("[DB ERROR] Error closing connection: " + e.getMessage());
            }
        }
    }
}
