package exception;

/**
 * Custom unchecked exception for data access layer errors.
 * Wraps low-level JDBC exceptions to prevent leaking persistence details.
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
