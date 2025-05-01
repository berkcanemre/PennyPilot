# PennyPilot
Capstone 1: Accounting Ledger Application - Java CLI Application

<p align="center"><strong>PennyPilot: Navigate your finances with precision and speed.</strong></p>
<p align="center"> <pre> About PennyPilot </pre> 
PennyPilot is a Java Command-Line Interface (CLI) application designed to help users manage and track their financial transactions — whether for personal use or small businesses.
Users can add deposits, record payments, view full transaction ledgers, and generate detailed financial reports with ease.
<p align="center"> <pre> Features  </pre> 

Add deposits and payments (debits)

View full ledger entries (newest first)

Filter by deposits, payments

Generate reports:

Month-to-date

Previous month

Year-to-date

Previous year

Search by vendor

Custom search by date, vendor, description, and amount

Saves data persistently to transactions.csv

<p align="center"> <pre> Screenshots  </pre>

Home Screen: [home.png](src/main/resources/Screenshoots/home.png)

Ledger Menu:[ledger.png](src/main/resources/Screenshoots/ledger.png)

Transactions:[transactions.png](src/main/resources/Screenshoots/Downloads/transactions.png)

Reports:[reports.png](src/main/resources/Screenshoots/reports.png)

Custom Search:[custom search.png](src/main/resources/Screenshoots/custom%20search.png)
.......................................................................
<p align="center"> <pre> Interesting Code Snippet  </pre>

// Save a new transaction to the CSV and keep data persistent

public void saveTransaction(Transaction t) 
{
    
try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
       
 writer.write(t.toCSV());
        
writer.newLine();
  
  } catch (IOException e) {
       
 System.out.println("Error writing file. Failed to save transaction: " + e.getMessage());
    }
}

//This method allows PennyPilot to append new transactions directly to the CSV file without overwriting previous entries, 
maintaining a running financial history.
<p align="center"> <pre> Getting Started  </pre>
Prerequisites:

Java 17+ installed

IDE (IntelliJ IDEA, VS Code, Eclipse) or just a terminal

Installation & Run

Clone the repository:
git clone https://github.com/berkcanemre/PennyPilot.git

Open the project in your IDE.

Run LedgerApp.java to start the application!

<p align="center"> <pre>  Technologies Used </pre>

Java 17

File I/O (BufferedReader/BufferedWriter)

Object-Oriented Programming (OOP)

Command-Line Interface (CLI)

<p align="center"> <pre> Author </pre>

Berkcan Emre

https://github.com/berkcanemre

<p align="center"> <pre> License </pre>

This project is open source and available under the MIT License.

<p align="center"> <pre> Acknowledgements </pre>

Special thanks to Yearup United’s staff and instructors, especially Cristopher Carter who inspired the project idea.

<p align="center"> <pre> Happy Accounting with PennyPilot! </pre>
