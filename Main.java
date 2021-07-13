package search;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String firstLine = input.nextLine();
        String word = input.nextLine();

        String result = "not found";
        String[] array = firstLine.split(" ");
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(word)) {
                result = Integer.toString(i + 1);
            }
        }
        System.out.println(result);
    }
}
