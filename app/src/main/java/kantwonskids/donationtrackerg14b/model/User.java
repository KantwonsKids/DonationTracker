package kantwonskids.donationtrackerg14b.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author Ethan Wilson <ewilson72@gatech.edu>
 * @version 1.1
 *
 * A basic user class
 */
public class User implements Parcelable, LabeledObject, Serializable {

    private String username;
    private String password;
    private UserRole role;
    private Location location;

    /**
     * Creates a new user with a given username, password, role, and assigned location.
     * The location must be null for all roles but location employees.
     *
     * @param username the username
     * @param password the password
     * @param role the user's role
     * @param location the user's location
     * @throws IllegalArgumentException If the role is not location employee and "location" is anything other than null
     */
    public User(String username, String password, UserRole role, Location location) {
        this.username = username;
        this.password = password;
        this.role = role;
        if (location != null && role != UserRole.LOCATION_EMPLOYEE) {
            throw new IllegalArgumentException("Only location employees can have assigned" +
                    "locations");
        }
        this.location = location;

    }

    /**
     * Creates a new user with a given username, password, and role.
     * If the new user is a location employee, an exception is thrown, as the location must be
     * specified.
     *
     * @param username the username
     * @param password the password
     * @param role the user's role
     */
    public User(String username, String password, UserRole role) {
        this(username, password, role, null);
        if (role == UserRole.LOCATION_EMPLOYEE) {
            throw new IllegalArgumentException("A location must be specified for location employees.");
        }
    }

    /**
     * Create a new user from a parcel.
     * @param in The parcel to create a new user from.
     */
    public User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        // this.location = in.readString(); idk what this parcel thing is but it is bad
        this.location = null;
        this.role = (UserRole)in.readSerializable();
    }

    /**
     * Returns a boolean value determining whether this user can update info at a certain location.
     * @param loc the location to update info at
     * @return true if the user can, false if it cannot
     * @throws IllegalArgumentException if the location is null
     */
    public boolean canUpdateDonationsAt(Location loc) {
        if (loc == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }

        int roleStatus = this.role.compareTo(UserRole.LOCATION_EMPLOYEE);

        if (roleStatus < 0) {
            // User is less than a donation employee, so it can update donations at no location.
            return false;
        } else if (roleStatus == 0) {
            // User is a location employee, so can only update donations at assigned location.
            return loc.equals(this.location);
        } else {
            // User is a manager or above, so can update donations at any location.
            return true;
        }
    }

    /**
     * Sets the username
     *
     * @param newUsername The new username to set
     */
    public void setUsername(String newUsername) {
        username = newUsername;
    }

    /**
     * Gets the username
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the password
     *
     * @param newPassword The new password to set
     */
    public void setPassword(String newPassword) {
        password = newPassword;
    }

    /**
     * Gets the password.
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the role of the current user.
     *
     * @param role the type of account that the user should have
     */
    public void setUserRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets the role of the current user.
     *
     * @return the type of account that the current user has
     */
    public UserRole getUserRole() {
        return this.role;
    }

    /**
     * Sets the location where this user works (Location employees only).
     *
     * @param location where this user works
     * @throws IllegalArgumentException if the user is not a location employee
     */
    public void setLocation(Location location) {
        if (this.role != UserRole.LOCATION_EMPLOYEE) {
            throw new IllegalArgumentException("User type "
                    + this.role.toString()
                    + " cannot be assigned to a location.");
        }
        this.location = location;
    }

    /**
     * Gets the location where this user works (Location employees only).
     *
     * @return the location described above.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Parcelable creator.
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        // dest.writeString(this.location);
        dest.writeSerializable(this.role);
    }

    @Override
    public String toString() {
        if (this.role == UserRole.GUEST) {
            return "Guest";
        }

        String str = "Username: " + this.username;
        switch (this.role) {
            case LOCATION_EMPLOYEE:
                str += "\nLocation Employee\nAssigned Location: " + location;
                break;
            case MANAGER:
                str += "\nManager";
                break;
            case ADMINISTRATOR:
                str += "\nAdministrator";
                break;
        }

        return str;
    }


    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof User)) {
            return false;
        }

        User u = (User) other;
        return this.username.equals(u.getUsername());
    }

    @Override
    public String getLabel() {
        return this.getUsername();
    }
}