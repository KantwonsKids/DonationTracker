package kantwonskids.donationtrackerg14b.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

public class SearchableList<T extends LabeledObject> extends ArrayList<T> implements Serializable {


    /**
     * Cutoff for the fuzzy search.
     */
    public static final int CUTOFF = 50;


    /**
     * Searches this list by a string query.
     * @param key the query to search this list's labels for
     * @return list of labels (NOT the actual objects) in searched order
     */
    public List<String> search(String key) {
        // Fuzzily search a list of the names of all the locations
        List<String> names = new LinkedList<>();
        this.forEach((item) -> names.add(item.getLabel()));
        List<ExtractedResult> results = FuzzySearch.extractSorted(key,
                names, CUTOFF);
        List<String> stringResults = new ArrayList<>();
        results.forEach((item) -> stringResults.add(item.getString()));

        // Create a reordering of the original list of locations
//        List<String> sortedList = new LinkedList<>();
//        for (ExtractedResult er : results) {
//            int index = er.getIndex();
//            sortedList.add(this.get(index));
//        }
        return stringResults;
    }
}
