package com.pluralsight;

// Shows options to filter and display ledger entries

import java.util.List;
import java.util.Scanner;

public class LedgerSubMenu {
    private Ledger ledger;
    private Scanner scanner = new Scanner(System.in);

    public LedgerSubMenu(Ledger ledger) {
        this.ledger = ledger;
    }

    public void show() {
        String choice = "";
        while (!choice.equalsIgnoreCase("H")) {
            System.out.println("\n=== Ledger Menu ===");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "A":
                    printTransactions(ledger.getTransactions());
                    break;
                case "D":
                    printTransactions(ledger.getDeposits());
                    break;
                case "P":
                    printTransactions(ledger.getPayments());
                    break;
                case "R":
                    ReportMenu reportMenu = new ReportMenu(ledger.getTransactions());
                    reportMenu.show();
                    break;
                case "H":
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void printTransactions(List<Transaction> list) {
        System.out.println("\n=== Transactions ===");
        for (Transaction t : list) {
            System.out.println(t);
        }
    }
}