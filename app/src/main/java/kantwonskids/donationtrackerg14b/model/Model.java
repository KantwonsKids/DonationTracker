package kantwonskids.donationtrackerg14b.model;

import java.util.List;

/**
 * @author Ethan Wilson
 * @author Juliana Petrillo
 * @version 2.0
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
    public static NameSearchableList<Location> donationDataList;

    /**
     * The user that is currently logged in.
     */
    private User loggedInUser;

    /**
     * The currently selected location;
     */
    private Location _currentLocation;


    private Donation _currentDonation;
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

    /**
     * @return the currently selected location.
     */
    public Location getCurrentLocation() {
        return _currentLocation;
    }

    /**
     * Sets the currently selected location.
     * @param location the currently selected location.
     */
    public void setCurrentLocation(Location location) {
        _currentLocation = location;
    }

    public Donation getCurrentDonation() {return _currentDonation;}

    public void setCurrentDonation(Donation donation) {_currentDonation = donation;}

    /**
     * Return the location with a given key.
     * @param key the key of the location to look for
     * @return the correct location or null if no such location exists
     */
    public Location getLocationByKey(int key) {
        for (Location d : donationDataList) {
            if (d.getKey() == key) {
                return d;
            }
        }
        return null;
    }

    /**
     * Return the location with a given name.
     * @param name the name of the location to look for
     * @return the correct location or null if no such location exists
     */
    public Location getLocationByName(String name) {
        for (Location d : donationDataList) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }

}
