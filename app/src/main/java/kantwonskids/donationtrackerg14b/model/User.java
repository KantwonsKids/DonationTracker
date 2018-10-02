package kantwonskids.donationtrackerg14b.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Ethan Wilson <ewilson72@gatech.edu>
 * @version 1.1
 *
 * A basic user class
 */
public class User extends Guest implements Parcelable {

    private String username;
    private String password;

    /**
     * Creates a new user with a given username and password
     *
     * Has to be public in order to create users outside of package
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Create a new user from a parcel.
     * @param in The parcel to create a new user from.
     */
    public User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
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
    }

    @Override
    public String toString() {
        return "Username: " + username;
    }
}