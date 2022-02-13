package happy.dev.userservice.exception;

public class ExceptionsToEmailProcessing extends RuntimeException{
    public ExceptionsToEmailProcessing() {
        super();
    }

    public ExceptionsToEmailProcessing(String message) {
        super(message);
    }

    public ExceptionsToEmailProcessing(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionsToEmailProcessing(Throwable cause) {
        super(cause);
    }

    protected ExceptionsToEmailProcessing(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
