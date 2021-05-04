package me.tim.oose.dea.mo;

public class User {
    private String user;
    private String password;
    private String token;
    private String firstName;
    private String lastName;

    public User(){
    }

    public User(String user, String password, String token, String firstName, String lastName) {
        this.user = user;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
