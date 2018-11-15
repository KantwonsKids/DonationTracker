package kantwonskids.donationtrackerg14b.model;


/**
 * @author Daniel Profili
 * @version 1.0
 * Enum for storing possible roles a user can have and abilities associated with these roles
 */

public enum UserRole {
    ADMINISTRATOR("Administrator", true, true, true, true, true),
    GUEST("Guest", false, false, false, false, false),
    LOCATION_EMPLOYEE("Location Employee", true, true, false, false, false),
    MANAGER("Manager", true, true, false, false, false),
    USER("User", true, false, false, false, false);

    // String representation for nice displaying
    private final String name;

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
    }

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Determines whether a user type can log in to the app (i.e. all types except guests).
//     * @return whether or not the user can log in
//     */
//    public boolean canLogIn() {
//        return this.canLogIn;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Determines whether the user type can update info.
//     * @return whether or not the user type can update info
//     */
//    public boolean canUpdateInfo() {
//        return this.canUpdateInfo;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Determines whether the user type can add/remove users.
//     * @return whether the user type can add/remove users
//     */
//    public boolean canAddOrRemoveUsers() {
//        return this.canAddOrRemoveUsers;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Determines whether the user type can lock/unlock users.
//     * @return whether the user type can lock/unlock users
//     */
//    public boolean canLockOrUnlockUsers() {
//        return this.canLockOrUnlockUsers;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Determines whether the user type can add/remove locations.
//     * @return whether the user type can add/remove locations
//     */
//    public boolean canAddOrRemoveLocations() {
//        return this.canAddOrRemoveLocations;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

    @Override
    public String toString() {
        return this.name;
    }

}
