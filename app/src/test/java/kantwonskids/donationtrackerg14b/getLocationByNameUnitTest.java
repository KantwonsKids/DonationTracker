package kantwonskids.donationtrackerg14b;

import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * @author Juliana Petrillo
 * @version 1.0
 *
 * JUnit Test to evaluate functionality of the Model class's
 * getLocationByName(String name) method.
 */
public class getLocationByNameUnitTest {

    private List<Location> locations = new ArrayList<>();
    private Model model = Model.getInstance();
    private Location location1 = new Location(
            0, "My House",
            32.00, -81.00,
            "25 Main St",
            "Savannah", "GA",
            31419,
            "Home",
            "(912) 555-6413",
            "www.myHouse.com");
    private Location location2 = new  Location(
            1, "Soho South Cafe",
            32.00, -80.00,
            "12 W Liberty St",
            "Savannah", "GA",
            31401,
            "Restaurant",
            "(912) 555-2400",
            "www.soho.com");
    private Location location3 = new  Location(
            2, "Savannah Christian Preparatory School",
            32.00, -82.00,
            "1599 Chatham Parkway",
            "Garden City", "GA",
            31408,
            "School",
            "(912) 555-2121",
            "www.savscps.com");
    private Location location4 = new  Location(
            3, "Blessed Sacrament Church",
            30.00, -82.00,
            "1003 E Victory Dr",
            "Savannah", "GA",
            31405,
            "Church",
            "(912) 555-6608",
            "www.blessedsacramentsavannah.com");

     @Before
    public void setUp() {

         locations.add(location1);
         locations.add(location2);
         locations.add(location3);
         locations.add(location4);

         model.locationList = locations;
     }

     @Test
    public void testGetLocationByName() {
         assertEquals(model.getLocationByName(
                 "My House"), location1);
         assertEquals(model.getLocationByName(
                 "Soho South Cafe"), location2);
         assertEquals(model.getLocationByName(
                 "Savannah Christian Preparatory School"), location3);
         assertEquals(model.getLocationByName(
                 "Blessed Sacrament Church"), location4);
     }

     @Test
    public void testLocationNotThere() {
         assertNull(model.getLocationByName("Your House"));
     }
}
