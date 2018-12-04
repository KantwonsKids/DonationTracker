package kantwonskids.donationtrackerg14b;

import android.os.Parcel;
import android.os.Parcelable;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;

import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.DonationCategory;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;


public class DonationTests {

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

    Donation[] donationArray = {donation1, donation2, donation3};

    @Test
    public void donationAttributes() {
        assertEquals(DonationCategory.CLOTHING, donationArray[0].getCategory());
        assertEquals(DonationCategory.ELECTRONICS, donationArray[1].getCategory());
        assertEquals(DonationCategory.HOUSEHOLD, donationArray[2].getCategory());

        assertEquals("Shirt", donationArray[0].getName());
        assertEquals("Shorts", donationArray[1].getName());
        assertEquals("Shoes", donationArray[2].getName());
    }

    @Test
    public void donationToString() {
        assertEquals("Clothing: Shirt", donationArray[0].toString());
        assertEquals("Electronics: Shorts", donationArray[1].toString());
        assertEquals("Household: Shoes", donationArray[2].toString());
    }
}