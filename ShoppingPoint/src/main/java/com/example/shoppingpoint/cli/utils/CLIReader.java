package com.example.shoppingpoint.cli.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CLIReader {
    public static String readline() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static int multiChoice(List<String> choices) throws IOException {
        for (int i = 0; i < choices.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, choices.get(i));
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
}
