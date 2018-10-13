package kantwonskids.donationtrackerg14b.model;

import java.util.List;

/**
 * @author Ethan Wilson
 *
 *
 * A singleton instance of the model that controllers
 * can access
 */
public class Model {
    // instance of the class
    private static Model _instance = new Model();

    /**
     * The list of registered users.
     */
    public UserList _userList;

    /**
     * A list of donationData objects
     */
    public List<DonationData> donationDataList;

    /**
     * The user that is currently logged in.
     */
    private User loggedInUser;



    /**
     * Gets the instance of the model class.
     * @return the instance of the Model, which stores all relevant application data
     */
    public static Model getInstance() {
        return _instance;
    }

    /**
     * Creates a new Model. Only used inside this class, since it's a singleton.
     */
    private Model() {
        _userList = new UserList();
    }

    /**
     * Sets the currently logged in user.
     * @param u the user currently logged in.
     */
    public void setCurrentUser(User u) {
        this.loggedInUser = u;
    }

    /**
     * Gets the active user. If the app is logged out, then this returns null.
     * @return The user currently logged in.
     */
    public User getCurrentUser() {
        return loggedInUser;
    }

}
