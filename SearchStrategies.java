package search;


import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface SearchStrategies {

    Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords);

    Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords, ArrayList<String> people);

}
