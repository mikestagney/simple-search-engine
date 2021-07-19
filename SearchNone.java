package search;

import java.util.*;

public class SearchNone implements SearchStrategies {

    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords) {
        return Collections.emptySet();
    }

    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords, ArrayList<String> people) {
        Set<Integer> lines  = new HashSet<>();
        for (int index = 0; index < people.size(); index++) {
            lines.add(index);
        }
        for (String word: queryWords) {
            if (invertedIndex.containsKey(word)) {
                lines.removeAll(invertedIndex.get(word));
            }
        }
        return lines;
    }

}
