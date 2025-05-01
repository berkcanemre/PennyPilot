package com.pluralsight;

//This class provides the main interface of the PennyPilot CLI app.
//It allows users to add deposits/payments and navigate to the ledger menu.
//Main class is the user interface. It shows a menu, reads input, and interacts with the backend classes.
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.List;
/**
 * Main class that handles user interface and application logic.
 */
public class Main {
    public static void main(String[] args) {
        Ledger ledger = new Ledger();         // Instance of the Ledger class
        Scanner scanner = new Scanner(System.in); // Scanner to read user input
        ledger.loadTransactions(); // Load from file on startup

        boolean running = true;

        // Main loop for the application
        while (running) {
            // Display menu
            System.out.println("\n------------------------------>PENNYPILOT<-------------------------------------");
            System.out.println("--------------->Welcome to your Personal Financial Advisor<--------------------");
            System.out.println("Please select an option: ");
            System.out.println("Press 'D' to Add Deposit");
            System.out.println("Press 'P' to Make Payment");
            System.out.println("Press 'L' to View Ledger");
            System.out.println("Press 'X' to Exit");
            String input = scanner.nextLine().trim().toUpperCase();

            // Handles user choices
            switch (input) {
                case "D":
                    addTransaction(scanner, ledger, true); // Add deposit
                    break;
                case "P":
                    addTransaction(scanner, ledger, false); // Add payment
                    break;
                case "L":
                    new LedgerSubMenu(ledger).show(); // Navigate to Ledger submenu
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select options displayed on the menu");
            }
        }

        System.out.println("Exiting PennyPilot. Thank you!");
        scanner.close();
    }

    // Adds a deposit or payment
    private static void addTransaction(Scanner scanner, Ledger ledger, boolean isDeposit) {
        System.out.print("Please Enter a Description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Please Enter a Vendor: ");
        String vendor = scanner.nextLine().trim();
        System.out.print("Please Enter an Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        if (!isDeposit) amount = -Math.abs(amount); // Payments are negative amounts

        // Create and save transaction
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        ledger.saveTransaction(t);
        System.out.println("Transaction saved.");
    }
    // Show the ledger
    private static void displayLedger(Scanner scanner, Ledger ledger) {
        List<Transaction> all = ledger.getAllTransactions();
        System.out.println("\n-----> Ledger <-----");
        for (Transaction t : all) {
            System.out.println(t);
        }
    }
}