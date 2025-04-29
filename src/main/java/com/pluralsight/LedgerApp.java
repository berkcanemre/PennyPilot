package com.pluralsight;

// Main entry point for PennyPilot

public class LedgerApp {
    public static void main(String[] args) {
        Ledger ledger = new Ledger();
        MenuHandler menu = new MenuHandler(ledger);

        System.out.println("Welcome to PennyPilot");
        menu.start();
    }
}