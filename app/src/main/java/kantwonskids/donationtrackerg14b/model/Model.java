package kantwonskids.donationtrackerg14b.model;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

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
    public List<Location> locationList;

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

//    /**
//     * @return the list of locations
//     */
//    public SearchableList<Location> getLocationList() { return locationList; }

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
        for (Location d : locationList) {
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
        for (Location d : locationList) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Gets a mapping of the location list's name to the object itself.
     * Used when searching.
     * @return a map of each location's name to its object reference
     */
    public Map<String, Location> getLabelLocationMap() {
        Map<String, Location> map = new HashMap<>();
        for (Location l : locationList) {
            map.put(l.getLabel(), l);
        }

        return map;
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

    /**
     * Gets all donations in every location. Used to search through all locations.
     * @return SearchableList of all donations currently stored.
     */
    public List<Donation> getAllDonations() {
        List<Donation> list = new ArrayList<>();
        for (Location l : locationList) {
            list.addAll(l.getDonations());
        }

        return list;

    }

    /**
     * Searches a list of labeled objects for the label query. Uses a fuzzy search and returns a
     * list sorted by similarity.
     * @param list the list to search
     * @param query the query to search for
     * @param <T> the type of the labeled object to search
     * @return list of objects sorted by search similarity
     * @throws IllegalArgumentException if query or list is null
     */
    public static <T extends LabeledObject> List<T> search(List<T> list, String query) {
        final int CUTOFF = 10;
        if (query == null || list == null) {
            throw new IllegalArgumentException("The query and the list must be non-null.");
        }

        // Return the same list if no query
        if (query.isEmpty()) {
            return list;
        }
        // Fuzzily search a list of the names of all the locations
        List<String> names = new ArrayList<>();
        list.forEach((item) -> names.add(item.getLabel()));
        List<ExtractedResult> results = FuzzySearch.extractSorted(query,
                names, CUTOFF);
//        List<String> stringResults = new ArrayList<>();
//        results.forEach((item) -> stringResults.add(item.getString()));

        // Create a reordering of the original list of locations
        List<T> sortedList = new ArrayList<>();
        for (ExtractedResult er : results) {
            int index = er.getIndex();
            sortedList.add(list.get(index));
        }

        return sortedList;

    }

}
