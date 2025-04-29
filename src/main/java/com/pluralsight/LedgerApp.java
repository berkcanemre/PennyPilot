package com.pluralsight;

// Main class to start the PennyPilot application

public class LedgerApp {
    public static void main(String[] args) {
        Ledger ledger = new Ledger();
        MenuHandler menu = new MenuHandler(ledger);

        System.out.println("Welcome to PennyPilot");
        menu.start();  // Start the Home Menu
    }
}