package happy.dev.userservice.exception;

public class NoUserDataException extends RuntimeException {
    public NoUserDataException() {
        super();
    }

    public NoUserDataException(String message) {
        super(message);
    }

    public NoUserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUserDataException(Throwable cause) {
        super(cause);
    }

    protected NoUserDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
