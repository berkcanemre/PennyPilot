package com.pluralsight;

// Class to manage transactions in memory and interact with the file handler

import java.util.List;

public class Ledger {
    private List<Transaction> transactions;
    private TransactionFileHandler fileHandler;

    // Constructor - loads existing transactions from file
    public Ledger() {
        fileHandler = new TransactionFileHandler();
        transactions = fileHandler.loadTransactions();
    }

    // Display all transactions (newest first)
    public void displayAllTransactions() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    // Add a new transaction (and save to file immediately)
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        fileHandler.saveTransaction(transaction);
    }
}