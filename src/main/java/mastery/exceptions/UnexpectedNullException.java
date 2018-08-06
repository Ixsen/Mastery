package mastery.exceptions;

/**
 * This class differenciates from the NullPointerException, since it is only for
 * situation where null should not have happened but did.
 */
public class UnexpectedNullException extends RuntimeException {
    public UnexpectedNullException() {
	super("Null should not have happened here");
    }

    public UnexpectedNullException(String message) {
	super(message);
    }

    public UnexpectedNullException(String message, Throwable cause) {
	super(message, cause);
    }
}
