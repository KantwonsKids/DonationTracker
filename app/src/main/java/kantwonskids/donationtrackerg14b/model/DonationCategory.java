package kantwonskids.donationtrackerg14b.model;

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
}
