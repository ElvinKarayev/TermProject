package Library.UserData;


public class User {
    private String Username;
    private String Password;

    public User() {
    }

    public User(String username, String Password) {
        this.Username = username;
        this.Password = Password;

    }

    public void setPassword(String password) {
        Password = password;
    }


    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public String toString() {
        return Username + "," + Password;

    }

}