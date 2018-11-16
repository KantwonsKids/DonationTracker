package kantwonskids.donationtrackerg14b.model;

/**
 * Represents a category of donation.
 */
public enum DonationCategory {
    CLOTHING("Clothing"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other");

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
     *  @param name name of the category
     *
     */
    DonationCategory(String name) {
        this.categoryName = name;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }

}
