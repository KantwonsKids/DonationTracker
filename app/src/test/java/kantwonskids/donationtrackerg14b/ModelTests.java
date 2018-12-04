package kantwonskids.donationtrackerg14b;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.OurLocation;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.Searchable;

import static kantwonskids.donationtrackerg14b.model.Model.search;
import static org.junit.Assert.*;

/**
 * Tests for the Model class.
 */
public class ModelTests {
    /**
     * A list used to test the search.
     */
    List<MockLabeledObject> testList;

    /**
     * Mock Searchable for testing the search method.
     */
    private static class MockLabeledObject implements Searchable {
        String label;

        MockLabeledObject(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return this.label;
        }
    }

    @Before
    /**
     * Initializes a sample list of labeled objects to search through, with
     * names "ab...", "bc...", ..., "...z" where each name is length nameLength.
     *
     * Each name overlaps nameLength - 1 chars with the next name, nameLength - 2
     * with the name after, etc.
     */
    public void initialize() {
        testList = new ArrayList<>();
        // Length of the name of the mock objects.
        final int nameLength = 4;
        for (int i = 0; i < 26 - nameLength; i++) {
            StringBuilder sb = new StringBuilder();

            // builds the name
            for (int j = i; j < nameLength + i; j++) {
                sb.append((char) (((int) 'a') + j));
            }

            String name = sb.toString();

            // add to list
            testList.add(new MockLabeledObject(name));
        }
    }

    /**
     * Tests search() with a list argument of null.
     * Should throw an IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void searchNullListTest() {
        search(null, "query");
    }

    /**
     * Tests search() with a query argument of null.
     * Should throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void searchNullQueryTest() {
        search(testList, null);
    }

    /**
     * Searching an empty list should return no results.
     */
    @Test
    public void searchEmptyListTest() {
        List<MockLabeledObject> results = search(new ArrayList<MockLabeledObject>(), "query");
        assertTrue("Searching an empty list should return no results.", results.isEmpty());
    }

    /**
     * Searching a list with an empty query should return the list unchanged.
     */
    @Test
    public void searchEmptyQueryTest() {
        List<MockLabeledObject> results = search(testList, "");
        assertEquals(results, testList);
    }

    /**
     * Searching for an item in a list with that item's exact name should return
     * that item as the first result.
     */
    @Test
    public void searchBasicTest() {
        for (MockLabeledObject obj : testList) {
            List<MockLabeledObject> results = search(testList, obj.getLabel());
            assertTrue(results.size() > 0);
            assertEquals(obj, results.get(0));
        }
    }

    /**
     * Searching a list with no match (not even with fuzzy) should return an empty list.
     */
    @Test
    public void searchNoMatchTest() {
        List<MockLabeledObject> results = search(testList, "$&#*$&#$*");
        assertTrue(results.isEmpty());
    }

    /**
     * Tests the fuzzy search in a naive way.
     * With the construction of the names in testList, testList[0] and testList[1]
     * share nameLength - 1 chars. So searching the list for the common chars should
     * return the first object, then the second, based on the fuzzy algorithm.
     */
    @Test
    public void searchFuzzyTest() {
        for (int i = 0; i < testList.size() - 1; i++) {
            MockLabeledObject obj1 = testList.get(i);
            MockLabeledObject obj2 = testList.get(i + 1);
            List<MockLabeledObject> results = search(testList, obj1.getLabel().substring(1, obj1.getLabel().length()));
            assertTrue(results.size() > 0);
            assertEquals(obj1, results.get(0));
            assertEquals(obj2, results.get(1));
        }
    }

    /**
     * getLocationByKey tests
     * @author Ethan Wilson
     */

    private Model model;

    /**
     * Basic test for a populated model.
     */
    @Test
    public void getLocationByKeyPopulatedModel_isCorrect() {
        populatedModel();
        assertEquals(model.getLocationByKey(50).getName(), "BOB");
    }

    /**
     * Basic test for an empty model.
     */
    @Test
    public void getLocationByKeyEmptyModel_isCorrect() {
        model = Model.getInstance();
        model.clearLocations();
//        model.locationList = new ArrayList<>();
        assertEquals(model.getLocationByKey(10), null);
    }

    private void populatedModel() {
        model = Model.getInstance();

        List<OurLocation> locationList = model.getLocationList();
        for (int i = 0; i < 100; i++)
        {
            if (i == 50) {
                locationList.add(new OurLocation.LocationBuilder().setKey(i).setName("BOB").setLatitude(5).setLongitude(5).setAddress("house").setCity("atl").setState("ga").setZipCode(12345).setType("big").setPhoneNumber("8675309").setWebsite("bob.com").setDonations(new ArrayList<Donation>()).createLocation());
                continue;
            }
            locationList.add(new OurLocation.LocationBuilder().setKey(i).setName("NOT BOB").setLatitude(5).setLongitude(5).setAddress("house").setCity("atl").setState("ga").setZipCode(12345).setType("big").setPhoneNumber("8675309").setWebsite("bob.com").setDonations(new ArrayList<>()).createLocation());
        }
//        model.locationList = locationList;
    }

    /**
     * getLocationByName Tests
     * @author Juliana Petrillo
     */

    private OurLocation location1 = new OurLocation.LocationBuilder().setKey(0).setName("My House").setLatitude(32.00).setLongitude(-81.00).setAddress("25 Main St").setCity("Savannah").setState("GA").setZipCode(31419).setType("Home").setPhoneNumber("(912) 555-6413").setWebsite("www.myHouse.com").createLocation();
    private OurLocation location2 = new OurLocation.LocationBuilder().setKey(1).setName("Soho South Cafe").setLatitude(32.00).setLongitude(-80.00).setAddress("12 W Liberty St").setCity("Savannah").setState("GA").setZipCode(31401).setType("Restaurant").setPhoneNumber("(912) 555-2400").setWebsite("www.soho.com").createLocation();
    private OurLocation location3 = new OurLocation.LocationBuilder().setKey(2).setName("Savannah Christian Preparatory School").setLatitude(32.00).setLongitude(-82.00).setAddress("1599 Chatham Parkway").setCity("Garden City").setState("GA").setZipCode(31408).setType("School").setPhoneNumber("(912) 555-2121").setWebsite("www.savscps.com").createLocation();
    private OurLocation location4 = new OurLocation.LocationBuilder().setKey(3).setName("Blessed Sacrament Church").setLatitude(30.00).setLongitude(-82.00).setAddress("1003 E Victory Dr").setCity("Savannah").setState("GA").setZipCode(31405).setType("Church").setPhoneNumber("(912) 555-6608").setWebsite("www.blessedsacramentsavannah.com").createLocation();

    @Before
    public void setUp() {
        OurLocation location1 = new OurLocation.LocationBuilder().setKey(0).setName("My House").setLatitude(32.00).setLongitude(-81.00).setAddress("25 Main St").setCity("Savannah").setState("GA").setZipCode(31419).setType("Home").setPhoneNumber("(912) 555-6413").setWebsite("www.myHouse.com").createLocation();
        OurLocation location2 = new OurLocation.LocationBuilder().setKey(1).setName("Soho South Cafe").setLatitude(32.00).setLongitude(-80.00).setAddress("12 W Liberty St").setCity("Savannah").setState("GA").setZipCode(31401).setType("Restaurant").setPhoneNumber("(912) 555-2400").setWebsite("www.soho.com").createLocation();
        OurLocation location3 = new OurLocation.LocationBuilder().setKey(2).setName("Savannah Christian Preparatory School").setLatitude(32.00).setLongitude(-82.00).setAddress("1599 Chatham Parkway").setCity("Garden City").setState("GA").setZipCode(31408).setType("School").setPhoneNumber("(912) 555-2121").setWebsite("www.savscps.com").createLocation();
        OurLocation location4 = new OurLocation.LocationBuilder().setKey(3).setName("Blessed Sacrament Church").setLatitude(30.00).setLongitude(-82.00).setAddress("1003 E Victory Dr").setCity("Savannah").setState("GA").setZipCode(31405).setType("Church").setPhoneNumber("(912) 555-6608").setWebsite("www.blessedsacramentsavannah.com").createLocation();
        model.clearLocations();
        model.getLocationList().add(location1);
        model.getLocationList().add(location2);
        model.getLocationList().add(location3);
        model.getLocationList().add(location4);
    }

    /**
     * Basic test for getting a location by name, when it exists.
     */
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

    /**
     * Basic test for getting a nonexistent location.
     */
    @Test
    public void testLocationNotThere() {
        assertNull(model.getLocationByName("Your House"));
    }
}
