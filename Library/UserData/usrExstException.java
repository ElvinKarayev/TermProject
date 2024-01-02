package Library.UserData;
/**
 * A custom exception class to represent the situation where a user already exists in the database.
 * This exception is thrown when an operation attempts to add a user to the database, but a user with
 * the same username already exists.
 */
public class usrExstException extends Error {
    public usrExstException() {
        super();
    }
}
