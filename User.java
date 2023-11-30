public class User{
    private String Username;
    private String Password;
    private int Index;
    

    public User(){
    }
    public User(String username, String Password){
        this.Username=username;
        this.Password=Password;
    }

    public void setIndex(int index) {
        Index = index;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setUsername(String username) {
        Username = username;
    }
    

    protected int getIndex() {
        return Index;
    }
    public String getPassword() {
        return Password;
    }
    public String getUsername() {
        return Username;
    }

}