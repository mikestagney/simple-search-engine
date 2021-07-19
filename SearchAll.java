package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SearchAll implements SearchStrategies {

    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex , String[] queryWords) {
        Set<Integer> lines  = new HashSet<>();
        if (invertedIndex.containsKey(queryWords[0])) {
            lines.addAll(invertedIndex.get(queryWords[0]));
        }
        for (int i = 1; i < queryWords.length; i++) {
            if (lines.size() > 0 && !invertedIndex.containsKey(queryWords[i])) {
                lines.removeAll(invertedIndex.get(queryWords[i]));
            }
        }
        return lines;

    }
    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords, ArrayList<String> people) {
        return search(invertedIndex, queryWords);

    }
}
