package search;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);;
    static ArrayList<String> people = new ArrayList<>();

    public static void main(String[] args) {
        enterAllPeople();

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
