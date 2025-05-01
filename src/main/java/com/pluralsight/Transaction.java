package com.pluralsight;

// This class represents a financial transaction in the ledger system.
// Each transaction stores the date, time, description, vendor, and amount.
//The Transaction class encapsulates individual entries and provides reusable parsing and formatting.

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Represents a single financial transaction.
 */
public class Transaction {
    // Core attributes of a transaction
    // Fields to store the transaction details
    private LocalDate date;          // Date of the transaction
    private LocalTime time;          // Time of the transaction
    private String description;      // Description (e.g., invoice, item purchased)
    private String vendor;           // Vendor involved in the transaction
    private double amount;           // Amount (positive = deposit, negative = payment)

    // Constructor: Used to initialize a transaction object
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    // Parses a transaction from a CSV string (method to parse a transaction from a CSV line)
    public static Transaction fromCSV(String line) {
        String[] parts = line.split("\\|"); // Split the line by '|'
        return new Transaction(
                LocalDate.parse(parts[0]),
                LocalTime.parse(parts[1]),
                parts[2],
                parts[3],
                Double.parseDouble(parts[4])
        );
    }
    // Converts the transaction to a line suitable for saving in a CSV file
    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
    // Returns a readable string for displaying transactions in console
    @Override
    public String toString() {
        return date + " " + time + " | " + description + " | " + vendor + " | " + amount;
    }

    // Getter methods (used for sorting and filtering)
    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }
}