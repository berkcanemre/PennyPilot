package com.pluralsight;

// This class handles the management of transactions.
// Responsibilities: load from file, save new transaction, filter by type.
//Ledger class acts as a controller: it handles file I/O, filtering, and sorting.

import java.io.*;
import java.util.*;

/**
 * Manages a list of transactions and handles loading/saving from CSV.
 */
public class Ledger {
    private List<Transaction> transactions = new ArrayList<>(); // Holds all transactions in memory
    private final String filePath = "src/main/resources/transactions.csv"; // Name of the CSV file used for storage

    // Constructor loads any existing transactions from the CSV file
    public Ledger() {
        loadTransactions();
    }

    // Loads transactions from file (.CSV in our project) into memory
    public void loadTransactions() {
        transactions.clear(); // Always start fresh
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transaction t = Transaction.fromCSV(line);
                if (t != null) { // Only add valid transactions
                    transactions.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            // If file doesn't exist, it will be created when saving
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Saves a new transaction and appends it to the CSV file
    public void saveTransaction(Transaction t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(t.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file. Failed to save transaction: " + e.getMessage());
        }
    }

    // Returns all transactions in reverse chronological order
    public List<Transaction> getAllTransactions() {
        List<Transaction> copy = new ArrayList<>(transactions);
        copy.sort((a, b) -> b.getDate().compareTo(a.getDate())); // Newest first
        return copy;
    }

    // Filters and returns only deposit transactions (positive amounts)
    public List<Transaction> getDeposits() {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                result.add(t);
            }
        }
        return result;
    }

    // Filters and returns only payment transactions (negative amounts)
    public List<Transaction> getPayments() {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                result.add(t);
            }
        }
        return result;
    }
}