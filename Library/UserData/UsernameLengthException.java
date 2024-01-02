package Library.UserData;
/**
 * This exception is thrown when a username does not meet the required length constraints.
 * It could be used during user registration or updating user information where the username length
 * is a critical factor for validation.
 */
public class UsernameLengthException extends Exception {
    public UsernameLengthException() {
        super();
    }
}
