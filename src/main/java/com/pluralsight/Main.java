package com.pluralsight;

// This class provides the main interface of the PennyPilot CLI app.
// It allows users to add deposits/payments and navigate to the ledger menu.
//Main class is the user interface. It shows a menu, reads input, and interacts with the backend classes.
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ledger ledger = new Ledger();         // Instance of the Ledger class
        Scanner scanner = new Scanner(System.in); // Scanner to read user input
        String choice = "";                   // Stores user’s menu choice

        // Main loop for the application
        while (!choice.equalsIgnoreCase("X")) {
            // Home menu display
            System.out.println("\n=== PennyPilot - Accounting Ledger ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextLine().trim();

            // Handle user choices
            switch (choice.toUpperCase()) {
                case "D":
                    addTransaction(scanner, ledger, true); // Add deposit
                    break;
                case "P":
                    addTransaction(scanner, ledger, false); // Add payment
                    break;
                case "L":
                    new LedgerSubMenu(ledger).show(); // Navigate to Ledger submenu (Day 2+)
                    break;
                case "X":
                    System.out.println("Exiting PennyPilot. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    // Prompts user for transaction info and creates the transaction
    private static void addTransaction(Scanner scanner, Ledger ledger, boolean isDeposit) {
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine().trim();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());
        if (!isDeposit) amount *= -1; // Payments are negative amounts

        // Create and save transaction
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        ledger.saveTransaction(t);
        System.out.println("Transaction saved.");
    }
}