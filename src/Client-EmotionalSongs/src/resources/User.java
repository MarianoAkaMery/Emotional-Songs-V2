package resources;

import java.sql.Date;

/**
 * The User class represents a user in the system.
 */
public class User {
    private String UserID;
    private String UserName;
    private String UserEmail;
    private String UserPassword;
    private Date UserLastUse;

    /**
     * Constructs a new User object with the provided user ID, name, email, password, and last use date.
     *
     * @param userId   The ID of the user
     * @param name     The name of the user
     * @param email    The email address of the user
     * @param password The password of the user
     * @param date     The last use date of the user
     */
    public User(String userId, String name, String email, String password, Date date) {
        this.UserID = userId;
        this.UserName = name;
        this.UserEmail = email;
        this.UserPassword = password;
        this.UserLastUse = date;
    }

    /**
     * Returns the user ID.
     *
     * @return The user ID
     */
    public String getUserID() {
        return UserID;
    }

    /**
     * Returns the user name.
     *
     * @return The user name
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * Returns the user email.
     *
     * @return The user email
     */
    public String getUserEmail() {
        return UserEmail;
    }

    /**
     * Returns the user password.
     *
     * @return The user password
     */
    public String getUserPassword() {
        return UserPassword;
    }

    /**
     * Returns the last use date of the user.
     *
     * @return The last use date of the user
     */
    public Date getUserLastUse() {
        return UserLastUse;
    }
}
