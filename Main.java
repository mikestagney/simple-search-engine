package search;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int number = Integer.parseInt(input.nextLine());

        ArrayList<String> people = new ArrayList<>();
        System.out.println("Enter all people:");
        for (int i = 0; i < number; i++) {
            String line = input.nextLine();
            people.add(line);
        }

        System.out.println("Enter the number of search queries:");
        int searchNumber = Integer.parseInt(input.nextLine());

        for (int i = 0; i < searchNumber; i++) {
            System.out.println("Enter data to search people:");
            String holder = input.nextLine();

            StringBuilder result = new StringBuilder();

            String temp = people.get(i);
            if (temp.toLowerCase(Locale.ROOT).contains(holder.toLowerCase(Locale.ROOT))) {
                result.append(temp);
                result.append("\n");
            }
            if (result == null) {
                System.out.println("No matching people found");
            } else {
                System.out.println(result);
            }
        }

    }
}
