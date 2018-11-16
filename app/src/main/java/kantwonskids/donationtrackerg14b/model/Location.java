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
public final class Location implements Searchable, Serializable, Parcelable {

    private int key;
    private final String name;
    private double latitude;
    private double longitude;
    private String address;
    private final String city;
    private final String state; // could be a (really big) enum with the 2 letter abbreviations
    private int zipCode;
    private String type;  // could be an enum
    private String phoneNumber; // String because it has the () and -
    private String website;
    private List<Donation> donations;

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
     * @param zipCode       zipCode of location
     * @param type          type of location
     * @param phoneNumber   phone number of location
     * @param website       website of location
     * @param donations     list of donations donated to the location
     */
    private Location(int key, String name, double latitude, double longitude, String address,
                    String city, String state, int zipCode, String type, String phoneNumber,
                    String website, List<Donation> donations) {
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.donations = donations;
    }

    /**
     * Constructs a Location object using a parcel.
     * @param in a parcel
     */
    private Location(Parcel in) {
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
                zipCode + "\n\n" + "Contact: " + "\n" +
                        phoneNumber + "\n" +
                        website + "\n" + type;
    }

    /**
     * Gets the key for a location
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * Gets the name of the location
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a LatLng object representing this location's position.
     * @return LatLng object of the latitude and longitude
     */
    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    /**
     * Gets the phone number of a location, formatted (XXX) XXX-XXXX
     * @return the number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

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

    /**
     * Adds a Donation to the collection for this location
     * @param donation a Donation
     */
    public void addDonation(Donation donation) {
        donations.add(donation);
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.city);
        dest.writeString(this.state);
    }
}
