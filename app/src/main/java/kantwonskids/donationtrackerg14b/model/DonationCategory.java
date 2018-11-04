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

    String categoryName;
    int menuId;

    /**
     * Creates a new donation category
     * @param name name of the category
     * @param id unique integer (used to populate the toolbar menu). Arbitrary value as long as it is unique.
     */
    DonationCategory(String name, int id) {
        this.categoryName = name;
        this.menuId = id;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }

    /**
     * Gets the menu ID.
     * @return the menu ID
     */
    public int getMenuId() {
        return menuId;
    }
}
