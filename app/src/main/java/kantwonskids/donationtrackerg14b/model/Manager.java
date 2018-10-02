package kantwonskids.donationtrackerg14b.model;

import android.os.Parcelable;

/**
 * Represents a manager, who has the following permissions:
 *     - All permissions of a location employee
 *     - Ability to modify information at any location
 */
public class Manager extends User implements Parcelable {
    /**
     * Creates a new manager.
     * @param username the manager's username
     * @param password the manager's password
     */
    public Manager(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString() {
        return super.toString() + "\nManager";
    }
}

