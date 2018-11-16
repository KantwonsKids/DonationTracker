package kantwonskids.donationtrackerg14b.model;

/**
 * @author Daniel Profili
 * @version 1.0
 * Enum for storing possible roles a user can have and abilities associated with these roles
 */

public enum UserRole {
    GUEST("Guest", false, false, false, false, false, false),
    USER("User", true, false, false, false, false, false),
    LOCATION_EMPLOYEE("Location Employee", true, true, false, false, false, false),
    MANAGER("Manager", true, false, true, false, false, false),
    ADMINISTRATOR("Administrator", true, false, true, true, true, true);

// --Commented out by Inspection START (11/15/2018 8:46 PM):
//    /*
//     * Enumerates the permissions for each user type.
//     *
//     * Users can:
//     *     - Log in
//     *     - View inventory and locations
//     *
//     * Location employees can:
//     *     - Do everything a user can
//     *     - Update inventories at one location
//     *
//     * Managers can:
//     *     - Do everything a user can
//     *     - Update information at all locations
//     *
//     * Administrators can:
//     *     - Do everything a manager can
//     *     - Add/remove users and locations
//     *     - Lock/unlock accounts
//     */
    private final boolean canLogIn;
    private final boolean canUpdateInfo;
    private final boolean canUpdateInfoAtAllLocations;
    private final boolean canAddOrRemoveUsers;
    private final boolean canLockOrUnlockUsers;
    private final boolean canAddOrRemoveLocations;

    // String representation for nice displaying
    private final String name;

    /**
     * Constructor. Initializes all the permissions for a user type.
     * @param canLogIn Whether the user type can log in
     * @param canUpdateInfo Whether the user type can update info at a single location
     * @param canUpdateInfoAtAllLocations whether the user type can update info at ALL locations
     * @param canAddOrRemoveUsers Whether the user type can add or remove users
     * @param canLockOrUnlockUsers Whether the user type can lock or unlock users
     * @param canAddOrRemoveLocations Whether the user type can add or remove locations
     */
    UserRole(String name, boolean canLogIn, boolean canUpdateInfo,
             boolean canUpdateInfoAtAllLocations, boolean canAddOrRemoveUsers,
             boolean canLockOrUnlockUsers, boolean canAddOrRemoveLocations) {
        this.name = name;
        this.canLogIn = canLogIn;
        this.canUpdateInfo = canUpdateInfo;
        this.canUpdateInfoAtAllLocations = canUpdateInfoAtAllLocations;
        this.canAddOrRemoveUsers = canAddOrRemoveUsers;
        this.canLockOrUnlockUsers = canLockOrUnlockUsers;
        this.canAddOrRemoveLocations = canAddOrRemoveLocations;
    }

   /**
     * Determines whether the user type can update info.
     * @return whether or not the user type can update info
     */
    public boolean canAddOrRemoveDonationsSpecificLocation() {
        return this.canUpdateInfo;
    }

    /**
     * Determines whether the user type can update info at all locations.
     * @return whether or not the user type can update info at all locations.
     */
    public boolean canAddOrRemoveDonationsAllLocations() {
        return this.canUpdateInfoAtAllLocations;
    }

    @Override
    public String toString() {
        return this.name;
    }


}
