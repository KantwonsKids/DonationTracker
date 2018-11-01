package kantwonskids.donationtrackerg14b.model;

/**
 * Represents a category of donation.
 */
public enum DonationCategory {
    CLOTHING ("Clothing"),
    KITCHEN ("Kitchen"),
    ELECTRONICS ("Electronics"),
    HOUSEHOLD ("Household"),
    OTHER ("Other");

    String categoryName;

    DonationCategory(String name) {
        this.categoryName = name;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }
}
