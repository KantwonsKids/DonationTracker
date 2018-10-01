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
    private String accountType;


    /**
     * Creates a new user with a given username and password
     *
     * Has to be public in order to create users outside of package
     *
     * @param u the username
     * @param p the password
     */
    public User(String u, String p, String t) {
        username = u;
        password = p;
        accountType = t;
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

    /**
     * Sets the account type
     *
     * @param x new account type
     */
    public void setAccountType(String x) {
        accountType = x;
    }

    /**
     * Gets the account type
     *
     * @return account type
     */
    public String getAccountType() {
        return accountType;
    }
}