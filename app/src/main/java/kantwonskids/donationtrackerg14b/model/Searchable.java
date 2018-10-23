package kantwonskids.donationtrackerg14b.model;

import java.util.Collection;
import java.util.List;

/**
 * Represents a class containing a list of objects with searchable parameters.
 * @param <I> The type of the object
 * @param <K> The type of the search key
 */
public interface Searchable<I, K> {

    /**
     * Default ratio cutoff for searching.
      */
    int CUTOFF = 50;

    /**
     * Searches a list of items for a search key
     * @param searchKey key to search for
     * @return search-sorted list of items, in order of relevance
     */
    List<I> search(K searchKey);
}
