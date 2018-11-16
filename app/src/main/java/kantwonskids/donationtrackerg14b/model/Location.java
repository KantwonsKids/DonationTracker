package kantwonskids.donationtrackerg14b.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

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
     * Builder for locations.
     */
    public static class LocationBuilder {

        private int key;
        private String name;
        private double latitude;
        private double longitude;
        private String address;
        private String city;
        private String state; // could be a (really big) enum with the 2 letter abbreviations
        private int zipCode;
        private String type;  // could be an enum
        private String phoneNumber; // String because it has the () and -
        private String website;
        private List<Donation> donations = new ArrayList<>();


        /**
         * Sets the key
         * @param key the key
         * @return this
         */
        public LocationBuilder setKey(int key) {
            this.key = key;
            return this;
        }

        /**
         * Sets the name
         * @param name the name
         * @return this
         */
        public LocationBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the latitude
         * @param latitude the latitude
         * @return this
         */
        public LocationBuilder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        /**
         * Sets the longitude
         * @param longitude the longitude
         * @return this
         */
        public LocationBuilder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        /**
         * Sets the address
         * @param address the address
         * @return this
         */
        public LocationBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * Sets the city
         * @param city the city
         * @return this
         */
        public LocationBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        /**
         * Sets the state
         * @param state the state
         * @return this
         */
        public LocationBuilder setState(String state) {
            this.state = state;
            return this;
        }

        /**
         * Sets the zip code
         * @param zipCode the zip code
         * @return this
         */
        public LocationBuilder setZipCode(int zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        /**
         * Sets the type
         * @param type the type
         * @return this
         */
        public LocationBuilder setType(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the phone number
         * @param phoneNumber the phoneNumber
         * @return this
         */
        public LocationBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Sets the website
         * @param website the website
         * @return this
         */
        public LocationBuilder setWebsite(String website) {
            this.website = website;
            return this;
        }

        /**
         * Sets the list of donations
         * @param donations the list of donations
         * @return this
         */
        public LocationBuilder setDonations(List<Donation> donations) {
            this.donations = donations;
            return this;
        }

        /**
         * Creates the location object from the options.
         * @return new location object
         */
        public Location createLocation() {
            return new Location(key, name, latitude, longitude, address, city, state, zipCode,
                    type, phoneNumber, website, donations);
        }
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
     * Constructs a Location object using a parcel.
     * @param in a parcel
     */
    public Location(Parcel in) {
        this.name = in.readString();
        this.city = in.readString();
        this.state = in.readString();
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

    /**
     * Returns a detail string containing all this location's details.
     * @return a string with details about the location
     */
    public CharSequence detailString() {
        return "Location: " + "\n" +
                address + "\n" +
                city + ", " +
                state + " " +
                //zipCode + "\n\n" + "Contact: " + "\n" +
                phoneNumber + "\n" +
                website + "\n" + type;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.city);
        dest.writeString(this.state);
    }

    /**
     * @return the lat and the lang
     */
    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }
}
