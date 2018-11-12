package kantwonskids.donationtrackerg14b;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GetLocationByKeyUnitTest {

    private Model model;
    @Test
    public void populatedModel_isCorrect() {
        populatedModel();
        assertEquals(model.getLocationByKey(50).getName(), "BOB");
    }

    @Test
    public void emptyModel_isCorrect() {
        model = Model.getInstance();
        model.locationList = new ArrayList<>();
        assertEquals(model.getLocationByKey(10), null);
    }

    private void populatedModel() {
        model = Model.getInstance();

        List<Location> locationList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            if (i == 50) {
                locationList.add(new Location(
                        i,
                        "BOB",
                        5,
                        5,
                        "house",
                        "atl",
                        "ga",
                        12345,
                        "big",
                        "8675309",
                        "bob.com",
                        new ArrayList<Donation>()
                        ));
                continue;
            }
            locationList.add(new Location(
                    i,
                    "NOT BOB",
                    5,
                    5,
                    "house",
                    "atl",
                    "ga",
                    12345,
                    "big",
                    "8675309",
                    "bob.com",
                    new ArrayList<Donation>()
            ));
        }
        model.locationList = locationList;
    }

    private String randomString()
    {
        Random rand = new Random();
        int len = rand.nextInt(20);
        String name = "";
        for (int i = 0; i < len; i++)
        {
            name += (char)(rand.nextInt(26) + 39);
        }
        return name;
    }


}