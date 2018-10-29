package kantwonskids.donationtrackerg14b.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class Model implements Serializable {
    // instance of the class
    private static Model _instance = new Model();

    /**
     * The list of registered users.
     */
    public UserList _userList;

    /**
     * A list of donationData objects
     */
    public NameSearchableList<Location> donationDataList;

    /**
     * The user that is currently logged in.
     * Transient so that it does not persist when saving
     */
    private transient User loggedInUser;

    /**
     * The currently selected location
     * Transient so that it does not persist when saving
     */
    private transient Location _currentLocation;

    /**
     * The currently selected donation
     * Transient so that it does not persist when saving
     */
    private transient Donation _currentDonation;
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
     * @return the list of locations
     */
    public NameSearchableList<Location> getDonationDataList() { return donationDataList; }

    /**
     * Sets the currently selected location.
     * @param location the currently selected location.
     */
    public void setCurrentLocation(Location location) {
        _currentLocation = location;
    }

    /**
     *
     * @return currently selected donation item
     */
    public Donation getCurrentDonation() {return _currentDonation;}

    /**
     *
     * @param donation sets currently selected donation item
     */
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

    /**
     * Saves the entire model to the phone
     */
    public static void saveToPhone(){
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS);
        File filename = new File(path, "/" + "donationTracker.ser");
        FileOutputStream fOS;
        ObjectOutputStream out;

        try {
            path.mkdirs();
            filename.createNewFile();
            fOS = new FileOutputStream(filename);
            out = new ObjectOutputStream(fOS);
            out.writeObject(_instance);
            out.close();
            fOS.close();
            Log.v("Serializing", "Wrote model to file successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * loads all saved data and places it in instance
     */
    public static void loadSavedData()
    {
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS);
        File filename = new File(path, "/" + "donationTracker.ser");
        FileInputStream fIS;
        ObjectInputStream in;


        try
        {
            fIS = new FileInputStream(filename);
            in = new ObjectInputStream(fIS);
            Object o = in.readObject();
            _instance = (Model)o;
            Log.v("Serialization", "Successfully loaded the model");
        }
        catch(Exception ex)
        {
            Log.v("Serialization Read Error : ",ex.getMessage());
            ex.printStackTrace();
        }
    }

}
