package kantwonskids.donationtrackerg14b.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ethan Wilson <ewilson72@gatech.edu>
 * @version 1.2
 *
 * A wrapper that stores all data of a specific donation
 */
public class Location implements LabeledObject, Serializable, Parcelable {

    private int key;
    private final String name;
    private double latitude;
    private double longitude;
    private String address;
    private final String city;
    private final String state; // could be a (really big) enum with the 2 letter abbreviations
    private int zipcode;
    private String type;  // could be an enum
    private String phoneNumber; // String because it has the () and -
    private String website;
    private List<Donation> donations;

    /**
     * A constructor for a location that takes in a variant of data types to give the
     * location its attributes
     *
     * @param key           key to identify different locations based on position
     * @param name          name of location
     * @param latitude      latitude coordinate of location
     * @param longitude     longitude coordinate  of location
     * @param address       street address of location
     * @param city          city that location is located in
     * @param state         state that location is located in
     * @param zipcode       zipcode of location
     * @param type          type of location
     * @param phoneNumber   phone number of location
     * @param website       website of location
     * @param donations     list of donations donated to the location
     */
    public Location(int key, String name, double latitude, double longitude, String address,
                    String city, String state, int zipcode, String type, String phoneNumber,
                    String website, List<Donation> donations) {
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.donations = donations;
    }

    /**
     * This constructor takes in all strings instead of a variant of data types
     *
     * @param key           key to identify location by position
     * @param name          name of location
     * @param latitude      latitude coordinate of location
     * @param longitude     longitude coordinate of location
     * @param address       street address of location
     * @param city          city that location is in
     * @param state         state that location is in
     * @param zipcode       zipcode of location
     * @param type          type of the location
     * @param phoneNumber   phone number of location
     * @param website       website of location
     */
    public Location(String key, String name, String latitude, String longitude, String address,
                    String city, String state, String zipcode, String type, String phoneNumber,
                    String website) {
        this.key = Integer.parseInt(key);
        this.name = name;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = Integer.parseInt(zipcode);
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.donations = new ArrayList<>();
    }



     /**
     * Constructor that takes in type-appropriate data but allows a user class
     * not to specify a list of donation items.
     *
     * @param key           key to identify location by position
     * @param name          name of location
     * @param latitude      latitude coordinate of location
     * @param longitude     longitude coordinate of location
     * @param address       street address of location
     * @param city          city that location is in
     * @param state         state that location is in
     * @param zipcode       zipcode of location
     * @param type          type of the location
     * @param phoneNumber   phone number of location
     * @param website       website of location
     */
    public Location(int key, String name, double latitude, double longitude, String address,
                    String city, String state, int zipcode, String type, String phoneNumber,
                    String website) {
        this(key, name, latitude, longitude, address, city, state, zipcode,
                type, phoneNumber, website, new ArrayList<>());
    }

    /**
     * Constructs a Location object using a parcel.
     * @param in a parcel
     */
    public Location(Parcel in) {
        this.name = in.readString();
        this.city = in.readString();
        this.state = in.readString();
    }

    /**
     * Returns a detail string containing all this location's details.
     * @return a string with details about the location
     */
    public CharSequence detailString() {
        return "Location: " + "\n" +
                        address + "\n" +
                        city + ", " +
                        state + " " +
                        zipcode + "\n\n" + "Contact: " + "\n" +
                        phoneNumber + "\n" +
                        website;
    }

    /**
     * Gets the key for a location
     * @return the key
     */
    public int getKey() {
        return key;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the key for a current location
//     * @param x a unique int for a certain location
//     */
//    public void setKey(int x) {
//        key = x;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the name of the location
     * @return the name
     */
    public String getName() {
        return name;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the name of a location.
//     * @param s a string representing the name
//     */
//    public void setName(String s) {
//        name = s;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the latitude of a location.
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the latitude of a location.
//     * @param d the double representing the latitude
//     */
//    public void setLatitude(double d) {
//        latitude = d;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the longitude of a location
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the longitude of a location.
//     * @param d a double representing the longitude
//     */
//    public void setLongitude(double d) {
//        longitude = d;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the address of a location.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the address of a location
//     * @param s a string representing the address
//     */
//    public void setAddress(String s) {
//        address = s;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the city in which the location is
     * @return the name of the city
     */
    public String getCity() {
        return city;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the city in which a location is
//     * @param s a string representing the city
//     */
//    public void setCity(String s) {
//        city = s;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the state that a location is in
     * @return the state
     */
    public String getState() {
        return state;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the state that the location is in
//     * @param s a string representing the state
//     */
//    public void setState(String s) {
//        state = s;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the zipcode that a location is in
     * @return an int representing the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets a location's zipcode
//     * @param x an int representing the zipcode
//     */
//    public void setZipcode(int x) {
//        zipcode = x;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Gets the type of location
//     * @return the type
//     */
//    public String getType() {
//        return type;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the type of the location
//     * @param s a String representing the type of location
//     */
//    public void setType(String s) {
//        type = s;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the phone number of a location, formatted (XXX) XXX-XXXX
     * @return the number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the phone number of a location
//     * @param s a phone number formatted (XXX) XXX-XXXX
//     */
//    public void setPhoneNumber(String s) {
//        phoneNumber = s;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Gets the website for a location
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the website for a location
//     * @param s a string representing the website
//     */
//    public void setWebsite(String s) {
//        website = s;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Returns a string representation of the location Name (City, State)
     * @return returns the toString of a location. A formatted version, containing
     * the location's name, city and state.
     */
    @Override
    public String toString() {
        return this.name + " (" + this.city + ", " + this.state + ")";
    }

    /**
     * Gets a collection of all donations made at this location
     * @return the collection of donations
     */
    public List<Donation> getDonations() {
        return donations;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the collection of all donations made at this location
//     * @param donations a list of Donations
//     */
//    public void setDonations(List<Donation> donations) {
//        this.donations = donations;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    /**
     * Adds a Donation to the collection for this location
     * @param donation a Donation
     */
    public void addDonation(Donation donation) {
        donations.add(donation);
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Adds multiple donations at once
//     * @param donations a collection of Donations
//     */
//    public void addDonation(List<Donation> donations) {
//        this.donations.addAll(donations);
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    @Override
    public String getLabel() {
        return this.name;
    }

    /**
     * Parcelable creator.
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.city);
        dest.writeString(this.state);
    }
}
