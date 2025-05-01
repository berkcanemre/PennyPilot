package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single financial transaction.
 */
public class Transaction {
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

    // Parses a transaction from a CSV string
    public static Transaction fromCSV(String line) {
        // Split the line by '|'
        String[] parts = line.split("\\|");

        // Ensure the parts length is valid
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid CSV format, expected 5 fields but got " + parts.length);
        }

        try {
            // Parse each part into the respective field
            LocalDate date = LocalDate.parse(parts[0]);
            // Parse the time with the correct format
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(parts[1], timeFormatter);
            String description = parts[2];
            String vendor = parts[3];
            double amount = Double.parseDouble(parts[4]);  // Parse amount

            return new Transaction(date, time, description, vendor, amount);
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            throw e;  // Re-throw the exception after logging it
        }
    }

    // Converts the transaction to a line suitable for saving in a CSV file
    public String toCSV() {
        // Format time to show only hours, minutes, and seconds (HH:mm:ss)
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return date + "|" + time.format(timeFormatter) + "|" + description + "|" + vendor + "|" + amount;
    }

    // Returns a readable string for displaying transactions in the console
    @Override
    public String toString() {
        // Format time to show only hours, minutes, and seconds (HH:mm:ss)
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return date + " " + time.format(timeFormatter) + " | " + description + " | " + vendor + " | " + amount;
    }

    // Getter methods
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