package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class TestLedger {

    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static ArrayList<Transaction> transactions;
    static {
        transactions = getTransactions();
    }


    // Utility method to prompt for common transaction details
    static Transaction getTransactionDetails(boolean isRecent, boolean isDeposit) {
        String description = Utilities.PromptForString("Please enter a short description for this transaction (e.g., 'Salary', 'Rent Payment'):  ");
        String vendor = Utilities.PromptForString("Please enter the name of the vendor or source (e.g., 'Amazon', 'Employer Name'):  ");
        double amount = Utilities.PromptForDouble("Please input the amount (e.g., 800.50):  ");

        // For payments, make the amount negative
        if (!isDeposit) {
            amount *= -1;
        }

        LocalDate date;
        LocalTime time;

        if (isRecent) {
            // Use current date and time for recent transactions
            date = LocalDate.now();
            time = LocalTime.now();
        } else {
            // Prompt for custom date and time for older transactions
            String dateInput = Utilities.PromptForString("Please enter the transaction date (format: YYYY-MM-DD, e.g., 2024-10-14):  ");
            String timeInput = Utilities.PromptForString("Please enter the transaction time (format: HH:mm:ss, e.g., 14:30:00):  ");
            date = LocalDate.parse(dateInput, formatter1);
            time = LocalTime.parse(timeInput, formatter2);
        }

        return new Transaction(date, time, description, vendor, amount);
    }

    // General method to add transactions
    static void addTransaction(boolean isRecent, boolean isDeposit) {
        Transaction transaction = getTransactionDetails(isRecent, isDeposit);
        transactions.add(transaction);
        System.out.println("You have successfully recorded the transaction!");
    }

    // Methods for adding deposits and payments
    static void addRecentDeposit() {
        addTransaction(true, true);
    }

    static void addOldDeposit() {
        addTransaction(false, true);
    }

    static void addRecentPayment() {
        addTransaction(true, false);
    }

    static void addOldPayment() {
        addTransaction(false, false);
    }

    public static void listTransaction(){
        System.out.println("List of All Transactions: ");
        for(Transaction t : transactions ){
            // Print each transaction in a formatted manner
            System.out.printf("%-12s %-10s %-30s %-15s %10s%n", t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount());}// Skip the first transaction and start from the second

    }

    public static ArrayList<Transaction> getTransactions()  {

        var transaction = new ArrayList<Transaction>();

        try {
            var bufferedReader = new BufferedReader(new FileReader("accountingLegerApplicationData.csv"));

            String input;
            bufferedReader.readLine();
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split(Pattern.quote("|"));

                LocalDate date = LocalDate.parse(tokens[0],formatter1);
                LocalTime time = LocalTime.parse(tokens[1],formatter2);

                transaction.add(new Transaction(date,time,tokens[2], tokens[3],Double.parseDouble(tokens[4])));
            }
            bufferedReader.close();

        } catch (Exception e){
            System.out.println("***ERROR!! ArrayList<Transactions>");
        }
        return transaction;
    }
}