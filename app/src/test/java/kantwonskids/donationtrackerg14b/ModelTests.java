package kantwonskids.donationtrackerg14b;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import kantwonskids.donationtrackerg14b.model.LabeledObject;

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
     * Mock LabeledObject for testing the search method.
     */
    private static class MockLabeledObject implements LabeledObject {
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
}
