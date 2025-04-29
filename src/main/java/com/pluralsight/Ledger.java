package com.pluralsight;

// Holds and manages all transactions in memory

import java.util.List;

public class Ledger {
    private List<Transaction> transactions;
    private TransactionFileHandler fileHandler;

    // Load transactions from file on start
    public Ledger() {
        fileHandler = new TransactionFileHandler();
        transactions = fileHandler.loadTransactions();
    }

    // Show all transactions, newest first
    public void displayAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    // Add a new transaction to the ledger and file
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        fileHandler.saveTransaction(transaction);
    }

    // Getter for later use (Day 3: filtering, reports)
    public List<Transaction> getTransactions() {
        return transactions;
    }
}