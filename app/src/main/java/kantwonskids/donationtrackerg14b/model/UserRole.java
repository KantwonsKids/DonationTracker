package kantwonskids.donationtrackerg14b.model;

import java.io.Serializable;

public enum UserRole implements Serializable {
    ADMINISTRATOR("Administrator", true, true, true, true, true),
    GUEST("Guest", false, false, false, false, false),
    LOCATION_EMPLOYEE("Location Employee", true, true, false, false, false),
    MANAGER("Manager", true, true, false, false, false),
    USER("User", true, false, false, false, false);

    /*
     * Enumerates the permissions for each user type.
     *
     * Users can:
     *     - Log in
     *     - View inventory and locations
     *
     * Location employees can:
     *     - Do everything a user can
     *     - Update inventories at one location
     *
     * Managers can:
     *     - Do everything a user can
     *     - Update information at all locations
     *
     * Administrators can:
     *     - Do everything a manager can
     *     - Add/remove users and locations
     *     - Lock/unlock accounts
     */
    private boolean canLogIn;
    private boolean canUpdateInfo;
    private boolean canAddOrRemoveUsers;
    private boolean canLockOrUnlockUsers;
    private boolean canAddOrRemoveLocations;

    // String representation for nice displaying
    private String name;

    /**
     * Constructor. Initializes all the permissions for a user type.
     * @param canLogIn Whether the user type can log in
     * @param canUpdateInfo Whether the user type can update info at one or more locations
     * @param canAddOrRemoveUsers Whether the user type can add or remove users
     * @param canLockOrUnlockUsers Whether the user type can lock or unlock users
     * @param canAddOrRemoveLocations Whether the user type can add or remove locations
     */
    UserRole(String name, boolean canLogIn, boolean canUpdateInfo, boolean canAddOrRemoveUsers,
             boolean canLockOrUnlockUsers, boolean canAddOrRemoveLocations) {
        this.name = name;
        this.canLogIn = canLogIn;
        this.canUpdateInfo = canUpdateInfo;
        this.canAddOrRemoveUsers = canAddOrRemoveUsers;
        this.canLockOrUnlockUsers = canLockOrUnlockUsers;
        this.canAddOrRemoveLocations = canAddOrRemoveLocations;
    }

    /**
     * Determines whether a user type can log in to the app (i.e. all types except guests).
     * @return whether or not the user can log in
     */
    public boolean canLogIn() {
        return this.canLogIn;
    }

    /**
     * Determines whether the user type can update info.
     * @return whether or not the user type can update info
     */
    public boolean canUpdateInfo() {
        return this.canUpdateInfo;
    }

    /**
     * Determines whether the user type can add/remove users.
     * @return whether the user type can add/remove users
     */
    public boolean canAddOrRemoveUsers() {
        return this.canAddOrRemoveUsers;
    }

    /**
     * Determines whether the user type can lock/unlock users.
     * @return whether the user type can lock/unlock users
     */
    public boolean canLockOrUnlockUsers() {
        return this.canLockOrUnlockUsers;
    }

    /**
     * Determines whether the user type can add/remove locations.
     * @return whether the user type can add/remove locations
     */
    public boolean canAddOrRemoveLocations() {
        return this.canAddOrRemoveLocations;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
