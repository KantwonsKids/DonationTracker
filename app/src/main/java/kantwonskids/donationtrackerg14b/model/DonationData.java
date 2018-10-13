package kantwonskids.donationtrackerg14b.model;

/**
 * @author Ethan Wilson <ewilson72@gatech.edu>
 * @version 1.1
 *
 * A wrapper that stores all data of a specific donation
 */
public class DonationData {

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

    /**
     * Constructor that takes in type-appropriate data
     */
    public DonationData(int key, String name, double latitude, double longitude, String address,
                        String city, String state, int zipcode, String type, String phoneNumber,
                        String website) {
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
    }

    /**
     * Constructor that takes in all Strings then automatically casts
     */
    public DonationData(String key, String name, String latitude, String longitude, String address,
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
    }

    public int getKey() {
        return key;
    }

    public void setKey(int x) {
        key = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double d) {
        latitude = d;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double d) {
        longitude = d;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String s) {
        address = s;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String s) {
        city = s;
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        state = s;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int x) {
        zipcode = x;
    }

    public String getType() {
        return type;
    }

    public void setType(String s) {
        type = s;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String s) {
        phoneNumber = s;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String s) {
        website = s;
    }
}
