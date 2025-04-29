package com.pluralsight;

// Class to handle user menu navigation for PennyPilot

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuHandler {
    private Ledger ledger;
    private Scanner scanner;

    // Constructor - takes a Ledger object to operate on
    public MenuHandler(Ledger ledger) {
        this.ledger = ledger;
        this.scanner = new Scanner(System.in);
    }

    // Start showing the Home Menu and handling user input
    public void start() {
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("\n=== PennyPilot Home Menu ===");
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
                    System.out.println("Goodbye! Thank you for using PennyPilot.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select D, P, L, or X.");
            }
        }
    }

    // Method to add a deposit
    private void addDeposit() {
        System.out.println("\n=== Add Deposit ===");
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
                amount  // Positive for deposit
        );

        ledger.addTransaction(transaction);
        System.out.println("Deposit added successfully!");
    }

    // Method to make a payment (debit)
    private void makePayment() {
        System.out.println("\n=== Make Payment ===");
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
                -Math.abs(amount)  // Ensure payment is negative
        );

        ledger.addTransaction(transaction);
        System.out.println("Payment recorded successfully!");
    }
}