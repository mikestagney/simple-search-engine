package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SearchAny implements SearchStrategies {

    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex , String[] queryWords) {
        Set<Integer> lines  = new HashSet<>();

        for (String word: queryWords) {
            if (invertedIndex.containsKey(word)) {
                lines.addAll(invertedIndex.get(word));
            }
        }
        return lines;

    }
    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords, ArrayList<String> people) {
        return search(invertedIndex, queryWords);

    }

}
