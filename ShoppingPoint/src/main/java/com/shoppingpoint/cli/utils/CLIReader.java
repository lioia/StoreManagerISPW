package com.shoppingpoint.cli.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CLIReader {
    private CLIReader() {
    }

    public static String readline() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static int multiChoice(List<String> choices) throws IOException {
        for (int i = 0; i < choices.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, choices.get(i));
        }
        while (true) {
            System.out.print("Select an option: ");
            int selected = Integer.parseInt(readline());
            if (selected <= 0 || selected > choices.size()) {
                System.out.println("Invalid input. Try again");
                continue;
            }
            return selected;
        }
    }

    public static boolean yesOrNo(String question) throws IOException {
        while (true) {
            System.out.println(question);
            String input = readline();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))
                return true;
            else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
                return false;
            else System.out.println("Invalid input. Try again.");
        }
    }

    public static String multiChoiceString(List<String> choices) throws IOException {
        for (String choice : choices) {
            System.out.println(choice);
        }
        while (true) {
            System.out.print("Insert a valid input: ");
            String value = readline();
            if (choices.contains(value)) return value;
            else System.out.println("Invalid input. Try again.");
        }
    }
}
