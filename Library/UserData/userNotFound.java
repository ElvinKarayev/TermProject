package Library.UserData;
/**
 * A custom exception class to represent the situation where a user is not found in the database.
 * This exception is thrown when an operation attempts to find a user with a specific username in the database
 * but the user does not exist.
 */
public class userNotFound extends Exception {
    public userNotFound() {
        super();
    }
}
