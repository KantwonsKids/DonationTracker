package kantwonskids.donationtrackerg14b.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents an employee at a specific donation center, with the following permissions:
 *     - All permissions of regular users
 *     - The ability to modify the inventory for a specific location
 */
public class LocationEmployee extends User implements Parcelable {
    /*
        TODO: Use a Location object instead of a string
     */
    private String location;

    /**
     * Creates a new location employee.
     * @param name the employee's username
     * @param password the employee's password
     * @throws IllegalArgumentException if the location is null or empty
     */
    public LocationEmployee(String name, String password, String location) {
        super(name, password);
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location must be non-null and nonempty.");
        }
        this.location = location;
    }

    /**
     * Create a new location employee from a parcel.
     * @param in the parcel to use to create the new location employee.
     */
    public LocationEmployee(Parcel in) {
        this(in.readString(), in.readString(), in.readString());
    }

    public static final Parcelable.Creator<LocationEmployee> CREATOR = new Parcelable.Creator<LocationEmployee>() {
        @Override
        public LocationEmployee createFromParcel(Parcel in) {
            return new LocationEmployee(in);
        }

        @Override
        public LocationEmployee[] newArray(int size) {
            return new LocationEmployee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.location);
    }

    @Override
    public String toString() {
        return super.toString() + "\nLocation Employee at: " + location;
    }
}
