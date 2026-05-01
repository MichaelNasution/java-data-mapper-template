package exception;

/**
 * Custom unchecked exception for service layer errors.
 * Used for validation failures and business rule violations.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
