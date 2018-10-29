package kantwonskids.donationtrackerg14b.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

public class NameSearchableList<T extends NamedObject> extends ArrayList<T> implements Searchable<T, String>, Serializable {

    @Override
    public List<T> search(String key) {
        // Fuzzily search a list of the names of all the locations
        List<String> names = new LinkedList<>();
        this.forEach((item) -> names.add(item.getName()));
        List<ExtractedResult> results = FuzzySearch.extractSorted(key,
                names, Searchable.CUTOFF);

        // Create a reordering of the original list of locations
        List<T> sortedList = new LinkedList<>();
        for (ExtractedResult er : results) {
            int index = er.getIndex();
            sortedList.add(this.get(index));
        }
        return sortedList;
    }
}
