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
            String[] wordsOnLine = people.get(index).split(" ");
            for (String word : wordsOnLine) {
                if (invertedIndex.containsKey(word)) {
                    Set<Integer> tempSet = invertedIndex.get(word);
                    tempSet.add(index);
                    invertedIndex.put(word, tempSet);
                } else {
                    Set<Integer> newSet = new HashSet<>(index);
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
        System.out.println("Enter a name or email to search all suitable people:");
        String query = getInputFromUser();

        boolean isFound = false;
        for (String person : people) {
            if (person.toLowerCase().contains(query.toLowerCase())) {
                System.out.println(person);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("No matching people found");
        }
    }
    static void printAllPeople() {
        System.out.println("=== List of people ===");
        people.forEach(System.out::println);
    }
    static String getInputFromUser() {
        return input.nextLine();
    }
}
