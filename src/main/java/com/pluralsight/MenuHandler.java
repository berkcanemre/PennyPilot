package com.pluralsight;

// Main Home Menu logic and user interaction

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuHandler {
    private Ledger ledger;
    private Scanner scanner;

    // Inject the ledger into the menu
    public MenuHandler(Ledger ledger) {
        this.ledger = ledger;
        this.scanner = new Scanner(System.in);
    }

    // Run the main menu loop
    public void start() {
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("\nPennyPilot Home Menu");
            System.out.println("[D] Add Deposit");
            System.out.println("[P] Make Payment (Debit)");
            System.out.println("[L] View Ledger");
            System.out.println("[X] Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledger.displayAllTransactions();
                    break;
                case "X":
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select D, P, L, or X.");
            }
        }
    }

    // Logic for adding a deposit
    private void addDeposit() {
        System.out.println("\nAdd Deposit");
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount // Deposit is positive
        );

        ledger.addTransaction(transaction);
        System.out.println("Deposit added successfully.");
    }

    // Logic for adding a payment
    private void makePayment() {
        System.out.println("\nMake Payment");
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                -Math.abs(amount) // Payment is always negative
        );

        ledger.addTransaction(transaction);
        System.out.println("Payment recorded successfully.");
    }
}