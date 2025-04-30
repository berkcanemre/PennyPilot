package com.pluralsight;

// This class handles the management of transactions.
// Responsibilities: load from file, save new transaction, filter by type.
//Ledger class acts as a controller: it handles file I/O, filtering, and sorting.
import java.io.*;
import java.util.*;

public class Ledger {
    private List<Transaction> transactions = new ArrayList<>(); // Holds all transactions in memory
    private final String FILE_NAME = "transactions.csv";         // Name of the CSV file used for storage

    // Constructor loads any existing transactions from the CSV file
    public Ledger() {
        loadTransactions();
    }

    // Reads all transactions from the CSV file into memory
    public void loadTransactions() {
        transactions.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("No existing ledger found. A new one will be created.");
        }
    }
    // Appends a new transaction to the file (persists it)
    public void saveTransaction(Transaction t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(t.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to save transaction: " + e.getMessage());
        }
    }
    // Returns all transactions, sorted with newest first
    public List<Transaction> getTransactions() {
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
        return transactions;
    }
    // Filters and returns only deposit transactions (positive amounts)
    public List<Transaction> getDeposits() {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) deposits.add(t);
        }
        return deposits;
    }
    // Filters and returns only payment transactions (negative amounts)
    public List<Transaction> getPayments() {
        List<Transaction> payments = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) payments.add(t);
        }
        return payments;
    }
}