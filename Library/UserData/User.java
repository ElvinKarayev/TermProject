package Library.UserData;

/**
 * Represents a user in the system. This class encapsulates user-related information 
 * and functionalities such as username, password, and other relevant attributes.
 * @author Elvin Garayev
 */
public class User {
    private String Username;
    private String Password;
    /**
     * Constructs a new user with default values for username and password.
     */
    public User() {
    }
    /**
     * Constructs a new user with the specified username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String Password) {
        this.Username = username;
        this.Password = Password;

    }
    /**
     * Sets the password for the user.
     *
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     * Sets the username for the user.
     *
     * @param username The new username to set.
     */
    public void setUsername(String username) {
        Username = username;
    }
    /**
     * Gets the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return Password;
    }
    /**
     * Gets the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return Username;
    }
    /**
     * Returns a string representation of the user in the format "username,password".
     *
     * @return A string representing the user.
     */
    public String toString() {
        return Username + "," + Password;

    }

}