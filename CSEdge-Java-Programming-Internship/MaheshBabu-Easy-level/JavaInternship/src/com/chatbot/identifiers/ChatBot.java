package com.chatbot.identifiers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I am your console-based chatbot. How can I assist you today?");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.toLowerCase().startsWith("open application")) {
                openApplication(input.substring("open application".length()).trim());
            } else if (input.toLowerCase().startsWith("search")) {
                searchWeb(input.substring("search".length()).trim());
            } else {
                System.out.println("Sorry, I didn't understand that command.");
            }
        }

        scanner.close();
    }

    private static void openApplication(String applicationName) {
        try {
            switch (applicationName.toLowerCase()) {
                case "notepad":
                    Runtime.getRuntime().exec("notepad");
                    break;
                case "calculator":
                    Runtime.getRuntime().exec("calc");
                    break;
                // Add more cases for other applications as needed
                default:
                    System.out.println("Application not recognized.");
            }
        } catch (IOException e) {
            System.out.println("Failed to open application: " + e.getMessage());
        }
    }

    private static void searchWeb(String query) {
        try {
            Desktop.getDesktop().browse(new URI("http://www.google.com/search?q=" + query));
        } catch (IOException | URISyntaxException e) {
            System.out.println("Failed to perform web search: " + e.getMessage());
        }
    }
}
