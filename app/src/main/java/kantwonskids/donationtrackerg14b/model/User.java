package kantwonskids.donationtrackerg14b.model;

/**
 * @author Ethan Wilson <ewilson72@gatech.edu>
 * @version 1.1
 *
 * A basic user class
 */
public class User {

    private String username;
    private String password;

    /**
     * Creates a new user with a given username and password
     *
     * Has to be public in order to create users outside of package
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Sets the username
     *
     * @param newUsername The new username to set
     */
    public void setUsername(String newUsername) {
        username = newUsername;
    }

    /**
     * Gets the username
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the password
     *
     * @param newPassword The new password to set
     */
    public void setPassword(String newPassword) {
        password = newPassword;
    }

    /**
     * Gets the password.
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }
}