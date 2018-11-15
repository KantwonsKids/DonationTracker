package kantwonskids.donationtrackerg14b.model;

/**
 * Represents a category of donation.
 */
public enum DonationCategory {
    CLOTHING ("Clothing", 101),
    KITCHEN ("Kitchen", 102),
    ELECTRONICS ("Electronics", 103),
    HOUSEHOLD ("Household", 104),
    OTHER ("Other", 105);

    final String categoryName;
    // --Commented out by Inspection (11/15/2018 6:26 PM):final int menuId;

    public static transient DonationCategory _currentDonation;

    /**
     * Sets the current donation category
     * @param category current donation category
     */
    public static void setCurrentDonationCategory(DonationCategory category) {
        _currentDonation = category;
    }

    /**
     * Creates a new donation category
     * @param name name of the category
     * @param id unique integer (used to populate the toolbar menu).
     *           Arbitrary value as long as it is unique.
     */
    DonationCategory(String name, int id) {
        this.categoryName = name;
        this.menuId = id;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Gets the menu ID.
//     * @return the menu ID
//     */
//    public int getMenuId() {
//        return menuId;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)
}
