package com.pluralsight;

// Class to handle reading and writing the transactions.csv file

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileHandler {
    private static final String FILE_PATH = "src/main/resources/transactions.csv";

    // Load all transactions from the CSV file into a list
    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
        return transactions;
    }

    // Save a new transaction by appending it to the CSV file
    public void saveTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(transaction.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing transaction: " + e.getMessage());
        }
    }
}