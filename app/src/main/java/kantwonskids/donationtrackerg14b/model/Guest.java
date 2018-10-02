package kantwonskids.donationtrackerg14b.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a guest, who has the following permissions:
 *     - Can view information at all locations without signing in
 */
public class Guest implements Parcelable {
    /**
     * Constructs a new guest.
     */
    public Guest() {
    }

    /**
     * The parcelable creator for guests. Does nothing, since guests hold no data.
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Guest>() {
        public Guest createFromParcel(Parcel in) {
            return new Guest();
        }

        public Guest[] newArray(int size) {
            return new Guest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

    }

    @Override
    public String toString() {
        return "Guest";
    }
}
