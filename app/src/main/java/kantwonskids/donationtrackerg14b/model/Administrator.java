package kantwonskids.donationtrackerg14b.model;

import android.os.Parcelable;

/**
 * Represents an administrator, who has the following permissions:
 *     - All permissions of managers
 *     - Add/remove users
 *     - Lock/unlock accounts
 */
public class Administrator extends User implements Parcelable {

    /**
     * Creates a new administrator.
     * @param name the username of the administrator account.
     * @param password the password of the administrator account.
     */
    public Administrator(String name, String password) {
        super(name, password);
    }

    @Override
    public String toString() {
        return super.toString() + "\nAdministrator";
    }
}
