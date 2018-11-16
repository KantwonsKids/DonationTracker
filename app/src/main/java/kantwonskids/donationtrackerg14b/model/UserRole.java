package kantwonskids.donationtrackerg14b.model;

/**
 * @author Daniel Profili
 * @version 1.0
 * Enum for storing possible roles a user can have and abilities associated with these roles
 */

public enum UserRole {
    GUEST("Guest", false, false),
    USER("User", false, false),
    LOCATION_EMPLOYEE("Location Employee", true, false),
    MANAGER("Manager", false, true),
    ADMINISTRATOR("Administrator", false, true);

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
    private final boolean canUpdateInfo;
    private final boolean canUpdateInfoAtAllLocations;

    // String representation for nice displaying
    private final String name;

    /**
     * Constructor. Initializes all the permissions for a user type.
     * @param canUpdateInfo Whether the user type can update info at a single location
     * @param canUpdateInfoAtAllLocations whether the user type can update info at ALL locations
     */
    UserRole(String name, boolean canUpdateInfo,
             boolean canUpdateInfoAtAllLocations) {
        this.name = name;
        this.canUpdateInfo = canUpdateInfo;
        this.canUpdateInfoAtAllLocations = canUpdateInfoAtAllLocations;
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
