package resources;

import java.sql.Date;

public class User {
    private String UserID;
    private String UserName;
    private String UserEmail;
    private String UserPassword;
    private Date UserLastUse;

    public User(String userId, String name, String email, String password, Date date) {
        this.UserID = userId;
        this.UserName = name;
        this.UserEmail = email;
        this.UserPassword = password;
        this.UserLastUse = date;
    }

    public String getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public Date getUserLastUse() {
        return UserLastUse;
    }
}
