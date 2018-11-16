package kantwonskids.donationtrackerg14b.model;

/**
 * Represents a category of donation.
 */
public enum DonationCategory {
    CLOTHING("Clothing", 101),
    KITCHEN("Kitchen", 102),
    ELECTRONICS("Electronics", 103),
    HOUSEHOLD("Household", 104),
    OTHER("Other", 105);

    final String categoryName;

    public static transient DonationCategory _currentDonation;

    /**
     * Sets the current donation category
     *
     * @param category current donation category
     */
    public static void setCurrentDonationCategory(DonationCategory category) {
        _currentDonation = category;
    }

    /**
     * Creates a new donation category
     *
     * @param name name of the category
     * @param id   unique integer (used to populate the toolbar menu).
     *             Arbitrary value as long as it is unique.
     */
    DonationCategory(String name, int id) {
        this.categoryName = name;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }

}
