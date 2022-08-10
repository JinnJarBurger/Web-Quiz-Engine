package net.therap.webQuizEngine.exception;

/**
 * @author adnan
 * @since 7/18/2022
 */
public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String message) {
        super(message);
    }
}
