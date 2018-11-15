package kantwonskids.donationtrackerg14b.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Locale;

/**
 * A class to represent a donation at a particular location.
 *
 * @author Juliana Petrillo
 * @version 1.0
 */
public class Donation implements Searchable, Serializable, Parcelable {

    private String time;
    private final String name;
    private final String description;
    private final float value;
    private final DonationCategory category;
    private final String comments;
    private final Location owner;


    /**
     * Constructor for a new Donation
     *
     * @param time          time of donation
     * @param item          item donated
     * @param description   description of donation
     * @param value         price of donation
     * @param category      category that donation is in
     * @param comments      additional comments about donation
     * @param owner         previous owner of item
     */
    public Donation(String time, String item, String description, float value,
                    DonationCategory category, String comments, Location owner) {
        this.time = time;
        this.name = item;
        this.description = description;
        this.value = value;
        this.category = category;
        this.comments = comments;
        this.owner = owner;
    }

    private Donation(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.comments = in.readString();
        this.value = in.readFloat();
        this.category = (DonationCategory)in.readSerializable();
        this.owner = in.readParcelable(Location.class.getClassLoader());
    }

    /**
     * Gets the name of the item that was donated.
     * @return the name
     */
    public CharSequence getName() {
        return name;
    }

    /**
     * Gets the category to which the item belongs
     * @return the category
     */
    public DonationCategory getCategory() {
        return category;
    }

    // --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Sets the time that the item was donated.
//     * @param time the time the item was donated
//     */
//    public void setTime(String time) {
//        this.time = time;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Sets the description of the item that was donated.
//     * @param description the description of the item
//     */
//    public void setDescription(String description) {
//        this.description = description;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Sets the name of the item that was donated.
//     * @param item the item name
//     */
//    public void setName(String item) {
//        this.name = item;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the value of the item that was donated.
//     * @param value the value in USD.
//     */
//    public void setValue(float value) {
//        this.value = value;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the category to which the item belongs
//     * @param category the category
//     */
//    public void setCategory(DonationCategory category) {
//        this.category = category;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

// --Commented out by Inspection START (11/15/18, 12:38 PM):
//    /**
//     * Sets the comments input by the employee who entered the item.
//     * @param comments the comments
//     */
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
// --Commented out by Inspection STOP (11/15/18, 12:38 PM)

    @Override
    public String toString() {
        return category.toString() + ": " + name;
    }

    @Override
    public String getLabel() {
        return this.name;
    }

    /**
     * Returns a detail string of this donation.
     * @return a string containing details for this donation.
     */
    public CharSequence detailString() {
        return "Category: " +
                        category + "\n" + "Price: $" +
                        String.format(Locale.ENGLISH, "%.2f", value) + "\n" + "Donated on: " +
                        time + "\n" + "Description: " +
                        description + "\n" + "Comments: " +
                        comments;
    }

    /**
     * Gets the toString() representation of this donation's owner location.
     * @return the string representation of this donation's owner location
     */
    public CharSequence getLocationString() {
        return this.owner.toString();
    }
    /**
     * Parcelable creator.
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Donation>() {
        @Override
        public Donation createFromParcel(Parcel in) {
            return new Donation(in);
        }

        @Override
        public Donation[] newArray(int size) {
            return new Donation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.comments);
        dest.writeFloat(this.value);
        dest.writeSerializable(this.category);
        dest.writeParcelable(this.owner, flags);
    }
}
