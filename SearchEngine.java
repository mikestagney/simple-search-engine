package search;

import java.util.*;

public class SearchEngine {

    private ArrayList<String> people;
    private Map<String, Set<Integer>> invertedIndex;
    private SearchStrategies method;

    SearchEngine() {
        people = new ArrayList<>();
        invertedIndex = new HashMap<>();
    }
    public void setSearchMethod(SearchStrategies method) {
        this.method = method;
    }
    public Set<Integer> search(String[] queryWords) {
        if (method instanceof SearchNone) {
            return this.method.search(invertedIndex, queryWords, people);
        } else {
            return this.method.search(invertedIndex, queryWords);
        }
    }

    public void addPerson(String person) {
        people.add(person);
    }
    public void setUpInvertedIndex() {
        for (int index = 0; index < people.size(); index++) {
            String[] wordsOnLine = people.get(index).toLowerCase().split(" ");
            for (String word : wordsOnLine) {
                if (invertedIndex.containsKey(word)) {
                    Set<Integer> tempSet = invertedIndex.get(word);
                    tempSet.add(index);
                    invertedIndex.put(word, tempSet);
                } else {
                    Set<Integer> newSet = new HashSet<>();
                    newSet.add(index);
                    invertedIndex.put(word, newSet);
                }
            }
        }
    }
    public void printSearchResults(Set<Integer> results) {
        if (results.size() > 0) {
            System.out.printf("%d persons found:\n", results.size());
            for (int currentIndex : results) {
                System.out.println(people.get(currentIndex));
            }
        } else {
            System.out.println("No matching people found");
        }
    }
    public void printAllPeople() {
        System.out.println("=== List of people ===");
        people.forEach(System.out::println);
    }



}
