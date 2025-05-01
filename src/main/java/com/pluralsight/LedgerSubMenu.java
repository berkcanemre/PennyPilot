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
            System.out.print("------------------------------------------------------------------------------");
            System.out.println("\n---------------------------> LEDGER MENU <------------------------------------");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Press 'A' to display All Entries");
            System.out.println("Press 'D' to display Deposits");
            System.out.println("Press 'P' to display Payments");
            System.out.println("Press 'R' to display Reports");
            System.out.println("Press 'H' to return Home Menu");
            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "A":
                    printTransactions(ledger.getAllTransactions());
                    break;
                case "D":
                    printTransactions(ledger.getDeposits());
                    break;
                case "P":
                    printTransactions(ledger.getPayments());
                    break;
                case "R":
                    ReportMenu reportMenu = new ReportMenu(ledger.getAllTransactions());
                    reportMenu.show();
                    break;
                case "H":
                    break;
                default:
                    System.out.println("Invalid choice. Please select options displayed on the menu");
            }
        }
    }

    private void printTransactions(List<Transaction> list) {
        System.out.print("------------------------------------------------------------------------------");
        System.out.println("\n---------------------------> TRANSACTIONS <-----------------------------------");
        System.out.println("------------------------------------------------------------------------------");
        for (Transaction t : list) {
            System.out.println(t);
        }
    }
}