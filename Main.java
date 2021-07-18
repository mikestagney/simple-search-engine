package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);;
    static ArrayList<String> people = new ArrayList<>();
    static Map<String, Set<Integer>> invertedIndex = new HashMap<>();

    public static void main(String[] args) {
        readPeopleFromFile(args);
        setUpInvertedIndex();

        while(true) {
            printMenu();
            String choice = getInputFromUser();
            switch (choice.charAt(0)) {
                case('1'):
                    search();
                    break;
                case('2'):
                    printAllPeople();
                    break;
                case('0'):
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
        }
    }
    static void setUpInvertedIndex() {
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
    static void readPeopleFromFile(String[] args) {
        String fileName = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--data") && i + 1 < args.length) {
                fileName = args[i + 1];
            }
        }
        if (fileName.equals("")) {
            System.out.println("No filename given!");
            return;
        }
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                people.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    static void enterAllPeople() {
        System.out.println("Enter the number of people:");
        int number = Integer.parseInt(getInputFromUser());

        System.out.println("Enter all people:");
        for (int i = 0; i < number; i++) {
            String line = getInputFromUser();
            people.add(line);
        }
    }
    static void printMenu() {
        System.out.println();
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }
    static void search() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = getInputFromUser().toUpperCase();

        System.out.println("Enter a name or email to search all suitable people:");
        String query = getInputFromUser().toLowerCase();

        String[] words = query.split(" ");
        Set<Integer> results;

        switch (strategy) {
            case("ANY"):
                results = searchAny(words);
                break;
            case("ALL"):
                results = searchAll(words);
                break;
            case("NONE"):
                results = searchNone(words);
                break;
            default:
                System.out.printf("%s strategy doesn't exist!\n", strategy);
                return;
        }
        printSearchResults(results);
    }
    static void printAllPeople() {
        System.out.println("=== List of people ===");
        people.forEach(System.out::println);
    }
    static String getInputFromUser() {
        return input.nextLine();
    }

    static Set<Integer> searchAny(String[] words) {
        Set<Integer> lines  = new HashSet<>();

        for (String word: words) {
            if (invertedIndex.containsKey(word)) {
                lines.addAll(invertedIndex.get(word));
            }
        }
        return lines;
    }
    static Set<Integer> searchAll(String[] words) {
        Set<Integer> lines  = new HashSet<>();
        if (invertedIndex.containsKey(words[0])) {
            lines.addAll(invertedIndex.get(words[0]));
        }
        for (int i = 1; i < words.length; i++) {
            if (lines.size() > 0 && !invertedIndex.containsKey(words[i])) {
                lines.removeAll(invertedIndex.get(words[i]));
            }
        }
        return lines;
    }
    static Set<Integer> searchNone(String[] words) {
        Set<Integer> lines  = new HashSet<>();
        for (int index = 0; index < people.size(); index++) {
            lines.add(index);
        }
        for (String word: words) {
            if (invertedIndex.containsKey(word)) {
                lines.removeAll(invertedIndex.get(word));
            }
        }
        return lines;
    }

    static void printSearchResults(Set<Integer> results) {
        if (results.size() > 0) {
            System.out.printf("%d persons found:\n", results.size());
            for (int currentIndex : results) {
                System.out.println(people.get(currentIndex));
            }
        } else {
            System.out.println("No matching people found");
        }
    }


}
