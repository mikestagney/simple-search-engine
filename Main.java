package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static SearchEngine searchEngine;

    public static void main(String[] args) {
        searchEngine = new SearchEngine();
        readPeopleFromFile(args);
        searchEngine.setUpInvertedIndex();

        while(true) {
            printMenu();
            String choice = getInputFromUser();
            switch (choice.charAt(0)) {
                case('1'):
                    search();
                    break;
                case('2'):
                    searchEngine.printAllPeople();
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
    static void search() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = getInputFromUser().toUpperCase();

        System.out.println("Enter a name or email to search all suitable people:");
        String query = getInputFromUser().toLowerCase();

        String[] words = query.split(" ");
        Set<Integer> results;

        switch (strategy) {
            case("ANY"):
                searchEngine.setSearchMethod(new SearchAny());
                break;
            case("ALL"):
                searchEngine.setSearchMethod(new SearchAll());
                break;
            case("NONE"):
                searchEngine.setSearchMethod(new SearchNone());
                break;
            default:
                System.out.printf("%s strategy doesn't exist!\n", strategy);
                return;
        }
        results = searchEngine.search(words);
        searchEngine.printSearchResults(results);
    }
    static void printMenu() {
        System.out.println();
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }
    static String getInputFromUser() {
        return input.nextLine();
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
                searchEngine.addPerson(scanner.nextLine());
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
            searchEngine.addPerson(line);
        }
    }
}
