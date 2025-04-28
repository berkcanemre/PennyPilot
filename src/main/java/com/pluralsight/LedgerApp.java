package com.pluralsight;

// Main class to start the application

public class LedgerApp {
    public static void main(String[] args) {
        Ledger ledger = new Ledger();

        System.out.println("=== Welcome to PennyPilot ===");
        System.out.println("Here are your transactions:");

        ledger.displayAllTransactions();
    }
}