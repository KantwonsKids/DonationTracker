package kantwonskids.donationtrackerg14b;

import android.os.Parcel;
import android.os.Parcelable;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.DonationCategory;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;

public class LocationTests {

    Model model = Model.getInstance();
    List<Location> locationList = model.getLocationList();

    Location loc1 = new Location(0,"Location 1", 100.0, 200.0,
            "Location 1 Address", "Atlanta", "Georgia", 30075,
            "Donation", "6788966603", "www.location1.com",
            new ArrayList<Donation>());
    Location loc2 = new Location(1,"Location 2", 100.0, 200.0,
            "Location 2 Address", "Roswell", "Georgia", 30062,
            "Donation", "6788966603", "www.location2.com",
            new ArrayList<Donation>());
    Location loc3 = new Location(2,"Location 3", 100.0, 200.0,
            "Location 3 Address", "Marietta", "Georgia", 30075,
            "Donation", "6788966603", "www.location3.com",
            new ArrayList<Donation>());

    LocalDateTime time1 = LocalDateTime.MAX;
    LocalDateTime time2 = LocalDateTime.now();

    String timeString1 = time1.toString();
    String timeString2 = time2.toString();

    String name1 = "Shirt";
    String name2 = "Shorts";
    String name3 = "Shoes";

    String description1 = "Red shirt";
    String description2 = "Blue shorts";
    String description3 = "Black shoes";

    float price1 = 10;
    float price2 = 20;
    float price3 = 30;

    DonationCategory category1 = DonationCategory.CLOTHING;
    DonationCategory category2 = DonationCategory.ELECTRONICS;
    DonationCategory category3 = DonationCategory.HOUSEHOLD;

    String comments1 = "This is a nice shirt";
    String comments2 = "These are nice shorts";
    String comments3 = "These are nice shoes";

    String ownerString1 = loc1.getName();
    String ownerString2 = loc2.getName();
    String ownerString3 = loc3.getName();

    Donation donation1 = new Donation(timeString1, name1, description1, price1, category1,
            comments1, ownerString1);
    Donation donation2 = new Donation(timeString1, name2, description2, price2, category2,
            comments2, ownerString2);
    Donation donation3 = new Donation(timeString2, name3, description3, price3, category3,
            comments3, ownerString3);



    Location[] locationArray = {loc1, loc2, loc3};
    //List<List<Donation>> donationlist = new ArrayList<>();

    @Test
    public void getLocationAttributes() {
        for (int i = 0; i < locationArray.length; i++) {
            assertEquals(i, locationArray[i].getKey());
        }

        for (Location loc : locationArray) {
            assertEquals(new LatLng(100.0,200.0), loc.getLatLng());
            assertEquals("6788966603", loc.getPhoneNumber());
        }

        for (int i = 0; i < locationArray.length; i++) {
            assertEquals("Location " + (i + 1), locationArray[i].getLabel());
        }
    }

    @Test
    public void locationToString() {
        assertEquals("Location 1 (Atlanta, Georgia)", locationArray[0].toString());
        assertEquals("Location 2 (Roswell, Georgia)", locationArray[1].toString());
        assertEquals("Location 3 (Marietta, Georgia)", locationArray[2].toString());
    }

    @Test
    public void locationDonation() {
        for (Location loc : locationArray) {
            loc.getDonations().add(donation1);
            loc.getDonations().add(donation2);
        }

        for (Location loc : locationArray) {
            assertEquals(donation1, loc.getDonations().get(0));
        }
    }
}
