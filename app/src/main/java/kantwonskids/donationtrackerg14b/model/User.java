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
     * @param u the username
     * @param p the password
     */
    User(String u, String p) {
        username = u;
        password = p;
    }

    /**
     * Sets the username
     *
     * @param x new username
     */
    public void setUsername(String x) {
        username = x;
    }

    /**
     * Gets the username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the password
     *
     * @param x new password
     */
    public void setPassword(String x) {
        password = x;
    }

    /**
     * Gets the password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }
}