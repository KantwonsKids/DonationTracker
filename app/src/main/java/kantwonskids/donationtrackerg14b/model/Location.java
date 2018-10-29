package kantwonskids.donationtrackerg14b.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ethan Wilson <ewilson72@gatech.edu>
 * @version 1.2
 *
 * A wrapper that stores all data of a specific donation
 */
public class Location implements NamedObject, Serializable {

    private int key;
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private String city;
    private String state; // could be a (really big) enum with the 2 letter abbreviations
    private int zipcode;
    private String type;  // could be an enum
    private String phoneNumber; // String because it has the () and -
    private String website;
    private List<Donation> donations;

    /**
     * Constructor that takes in type-appropriate data
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
     * Constructor that takes in all Strings then automatically casts
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
     */
    public Location(int key, String name, double latitude, double longitude, String address,
                    String city, String state, int zipcode, String type, String phoneNumber,
                    String website) {
        this(key, name, latitude, longitude, address, city, state, zipcode,
                type, phoneNumber, website, new ArrayList<>());
    }

    /**
     * Gets the key for a location
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the key for a current location
     * @param x a unique int for a certain location
     */
    public void setKey(int x) {
        key = x;
    }

    /**
     * Gets the name of the location
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of a location.
     * @param s a string representing the name
     */
    public void setName(String s) {
        name = s;
    }

    /**
     * Gets the latitude of a location.
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of a location.
     * @param d the double representing the lattitude
     */
    public void setLatitude(double d) {
        latitude = d;
    }

    /**
     * Gets the logitude of a location
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of a location.
     * @param d a double representing the longitude
     */
    public void setLongitude(double d) {
        longitude = d;
    }

    /**
     * Gets the address of a location.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of a location
     * @param s a string representing the address
     */
    public void setAddress(String s) {
        address = s;
    }

    /**
     * Gets the city in which the location is
     * @return the name of the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city in which a location is
     * @param s a string representing the city
     */
    public void setCity(String s) {
        city = s;
    }

    /**
     * Gets the state that a location is in
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state that the location is in
     * @param s a string representing the state
     */
    public void setState(String s) {
        state = s;
    }

    /**
     * Gets the zipcode that a location is in
     * @return an int representing the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * Sets a location's zipcode
     * @param x an int representing the zipcode
     */
    public void setZipcode(int x) {
        zipcode = x;
    }

    /**
     * Gets the type of location
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the location
     * @param s a String representing the type of location
     */
    public void setType(String s) {
        type = s;
    }

    /**
     * Gets the phone number of a location, formatted (XXX) XXX-XXXX
     * @return the number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of a location
     * @param s a phone number formatted (XXX) XXX-XXXX
     */
    public void setPhoneNumber(String s) {
        phoneNumber = s;
    }

    /**
     * Gets the website for a location
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the website for a location
     * @param s a string representing the website
     */
    public void setWebsite(String s) {
        website = s;
    }

    /**
     * Returns a string representation of the location Name (City, State)
     * @return
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

    /**
     * Sets the collection of all donations made at this loction
     * @param donations a list of Donations
     */
    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    /**
     * Adds a Donation to the collection for this location
     * @param donation a Donation
     */
    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    /**
     * Adds multiple donatinos at once
     * @param donations a collection of Donations
     */
    public void addDonation(List<Donation> donations) {
        this.donations.addAll(donations);
    }
}
