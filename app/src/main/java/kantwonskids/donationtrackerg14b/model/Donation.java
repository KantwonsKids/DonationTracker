package kantwonskids.donationtrackerg14b.model;

import java.time.LocalDateTime;

/**
 * A class to represent a donation at a particular location.
 *
 * @author Juliana Petrillo
 * @version 1.0
 */
public class Donation {

    private LocalDateTime time;
    private String name;
    private String description;
    private float value;
    private DonationCategory category;
    private String comments;
    // TODO: add an instance variable for image

    Donation(LocalDateTime time, String item, String description, float value,
             DonationCategory category, String comments) {
        this.time = time;
        this.name = item;
        this.description = description;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }

    /**
     * Gets the time that the item was donated.
     * @return the time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Gets the description of the item that was donated.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the name of the item that was donated.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the item that was donated.
     * @return the value in USD.
     */
    public float getValue() {
        return value;
    }

    /**
     * Gets the category to which the item belongs
     * @return the category
     */
    public DonationCategory getCategory() {
        return category;
    }

    /**
     * Gets the comments input by the employee who entered the item.
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the time that the item was donated.
     * @param time the time the item was donated
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Sets the description of the item that was donated.
     * @param description the description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the name of the item that was donated.
     * @param item the item name
     */
    public void setName(String item) {
        this.name = item;
    }

    /**
     * Sets the value of the item that was donated.
     * @param value the value in USD.
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Sets the category to which the item belongs
     * @param category the category
     */
    public void setCategory(DonationCategory category) {
        this.category = category;
    }

    /**
     * Sets the comments input by the employee who entered the item.
     * @param comments the comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

}
