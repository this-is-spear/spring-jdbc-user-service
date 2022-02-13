package happy.dev.userservice.exception;

public class ExceptionsToPasswordProcessing extends RuntimeException{
    public ExceptionsToPasswordProcessing() {
        super();
    }

    public ExceptionsToPasswordProcessing(String message) {
        super(message);
    }

    public ExceptionsToPasswordProcessing(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionsToPasswordProcessing(Throwable cause) {
        super(cause);
    }

    protected ExceptionsToPasswordProcessing(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
