package kantwonskids.donationtrackerg14b.model;

/**
 * Represents an object with a name parameter.
 * Used to search/sort donations, locations, and users by name.
 */
public interface Searchable {
    /**
     * Gets the searchable label of this object.
     * @return the object's label
     */
    String getLabel();
}
