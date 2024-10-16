package com.pluralsight;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Main {
    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static ArrayList<Transaction> transactions;

    static {
        transactions = getTransactions();
    }

    public static ArrayList<Transaction> getTransactions() {

        var transaction = new ArrayList<Transaction>();

        try {
            var bufferedReader = new BufferedReader(new FileReader("accountingLegerApplicationData.csv"));
            String input;
            bufferedReader.readLine();
            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split(Pattern.quote("|"));

                LocalDate date = LocalDate.parse(tokens[0], formatter1);
                LocalTime time = LocalTime.parse(tokens[1], formatter2);

                transaction.add(new Transaction(date, time, tokens[2], tokens[3], Double.parseDouble(tokens[4])));
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("***ERROR!! ArrayList<Transactions>");
            System.out.println(e.getMessage());
        }
        return transaction;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("----------     Welcome to Accounting Ledger Application!     ----------");
        System.out.println("-----------------------------------------------------------------------\n");

        boolean exitApp = true;
        while (exitApp) {
            appHomeScreen();
            char command = Utilities.PromptForChar("Please Type and Enter From: [D], [P], [L], [X] :  ");
            switch (command) {

                case 'D':
                    addDeposit();
                    break;
                case 'P':
                    addPayment();
                    break;
                case 'L':
                    ledgerMenu();

                    break;
                case 'X':
                    System.out.println("Thank you for choosing Us! ");
                    exitApp = false;
                    break;
                default:
                    System.out.println("*** ERROR! ***  Invalid Selection: Please Type and Enter From: [D], [P], [L], [X] : ");
            }
        }
    }

    // Top level menu,
    static void appHomeScreen() {
        // System.out.println("-----------------------------------------------------------------------\n");
        System.out.println("--------------- This is Your Home Screen Menu Please Select ----------------");
        System.out.println("D)      Add Deposit");
        System.out.println("P)      Make Payment (Debit)");
        System.out.println("L)      Show Ledger");
        System.out.println("X)      Exit Application\n");
    }

    // Method to add Deposit
    static void addDeposit() {
        //System.out.println("This is your Deposit Menu: \n");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-------------------- This is Your Deposit Menu ----------------------");
        System.out.println("-----------------------------------------------------------------------\n");
        boolean exitApp = true;

        while (exitApp) {
            System.out.println("-------------------- Please Select From The Following ----------------");
            //(today’s date and time will be auto-filled)
            System.out.println("N) Add a recent deposit ");
            //(prompted for the date and time)
            System.out.println("O) Add an older deposit ");
            System.out.println("X) To return to Main Menu\n");
            char command = Utilities.PromptForChar("Please Type and Enter From: [O] | [N] | [X]  ");
            switch (command) {

                case 'N':
                    addRecentDeposit();
                    break;
                case 'O':
                    addOldDeposit();
                    break;
                case 'X':
                    exitApp = false;
                    break;
                default:
                    System.out.println("*** ERROR! ***  Invalid Selection: Please Type and Enter From: [O] | [N] | [X] : ");


            }
        }
    }

    // Method to make Payment
    static void addPayment() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-------------------- This is Your Payment Menu ----------------------");
        System.out.println("-----------------------------------------------------------------------\n");
        boolean exitApp = true;

        while (exitApp) {
            System.out.println("-------------------- Please Select From The Following ----------------");
            //(today’s date and time will be auto-filled)
            System.out.println("N) Add a recent Payment ");
            //(prompted for the date and time)
            System.out.println("O) Add an older Payment ");
            System.out.println("X) To return to Main Menu\n");
            char command = Utilities.PromptForChar("Please Type and Enter From: [O] | [N] | [X]  ");
            switch (command) {
                // change from here
                case 'N':
                    addRecentPayment();
                    break;
                case 'O':
                    addOldPayment();
                    break;
                case 'X':
                    exitApp = false;
                    break;
                default:
                    System.out.println("*** ERROR! ***  Invalid Selection: Please Type and Enter From: [O] | [N] | [X] : ");

            }
        }
    }

    static Transaction getTransactionDetails(boolean isRecent, boolean isDeposit) {
        String description = Utilities.PromptForString("Please enter a short description for this transaction (e.g., 'Salary', 'Rent Payment'):  ");
        String vendor = Utilities.PromptForString("Please enter the name of the vendor or source (e.g., 'Amazon', 'Employer Name'):  ");
        double amount = Utilities.PromptForDouble("Please input the amount (e.g., 800.50):  ");

        // To make the amount negative
        if (!isDeposit) {
            amount *= -1;
        }

        LocalDate date;
        LocalTime time;

        if (isRecent) {
            // Use current date and time
            date = LocalDate.now();
            time = LocalTime.now();
        } else {
            while (true) {
                // Prompt older transactions
                try {
                    String dateInput = Utilities.PromptForString("Please enter the transaction date (format: YYYY-MM-DD, e.g., 2024-10-14):  ");
                    String timeInput = Utilities.PromptForString("Please enter the transaction time (format: HH:mm:ss, e.g., 14:30:00):  ");

                    date = LocalDate.parse(dateInput, formatter1);
                    time = LocalTime.parse(timeInput, formatter2);
                    break;
                } catch (Exception e) {
                    System.out.println("ERROR! - Invalid date and time Input!");
                }
            }
        }
        return new Transaction(date, time, description, vendor, amount);
    }

    static void addTransaction(boolean isRecent, boolean isDeposit) {
        Transaction transaction = getTransactionDetails(isRecent, isDeposit);
        transactions.add(transaction);
        System.out.println("You have successfully recorded the transaction!");
    }

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


    //Ledger - Display all Entries
    static void displayAllEntriesLedgers(){
        System.out.println("All the Entries: ");
        listTransaction();
    }
    // Display only Deposits
    static void displayDepositsLedger(){
        System.out.println("All Deposits Only: ");
        printArray(getDepositsFromFullLedger(transactions));
    }
    // Display only Payments
    static void displayPaymentsLedger(){
        System.out.println("All Payments Only: ");
        printArray(getPaymentsFromFullLedger(transactions));
    }


    //Method to display Ledger // WOP
    static void ledgerMenu() {

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("---------------------- This is Your Ledger Menu ------------------------");
        System.out.println("-----------------------------------------------------------------------\n");
        boolean exitApp = true;

        while (exitApp) {
            System.out.println("-------------------- Please select from the following ----------------");
            System.out.println("A) View All Transactions ");
            System.out.println("D) View Deposits ");
            System.out.println("P) View Payments");
            System.out.println("R) Generate Reports ");
            char command = Utilities.PromptForChar("Please Type and Enter From: [A] | [D] | [P] | [R]   ");
            switch (command) {

                case 'N':
                    addRecentDeposit();
                    break;
                case 'O':
                    addOldDeposit();
                    break;
                case 'X':
                    exitApp = false;
                    break;
                default:
                    System.out.println("*** ERROR! ***  Invalid Selection: Please Type and Enter From: [O] | [N] | [X] : ");


            }
        }

    }

    // Display pre-defined Reports menu /// WOP
    static void displayReportsLedger() {
        //System.out.println("This is your Deposit Menu: \n");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-------------- This is your pre-defined Report section ----------------");
        System.out.println("-----------------------------------------------------------------------\n");
        boolean exitApp = true;

        while (exitApp) {
            System.out.println("-------------------- Please select from the following ----------------");

            System.out.println("1) This Month's Transactions ");
            System.out.println("2) Last Month's Transactions ");
            System.out.println("3) This Year's Transactions ");
            System.out.println("4) Last Year's Transactions ");
            System.out.println("5) Find Transactions by Vendor ");
            System.out.println("0) Return to Report Menu ");
            System.out.println("H) Return to Home Screen ");

            System.out.println("X) To return to Main Menu\n");
            char command = Utilities.PromptForChar("Please Type and Enter From: [1] | [2] | [3] | [4] | [5] | [0] | [H]  ");
            switch (command) {

                case 'N':

                    break;
                case 'O':

                    break;
                case 'X':
                    exitApp = false;
                    break;
                default:
                    System.out.println("*** ERROR! ***  Invalid Selection: Please Type and Enter From: [O] | [N] | [X] : ");
            }
        }
    }


  static void displayThisMonthTransaction(){
        for(Transaction t : transactions ){
            if(true) /// the argument!
            // Print each transaction in a formatted manner
            System.out.printf(
                    "%-12s %-10s %-30s %-15s %10s%n",
                    t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
            );
        }
    }








    public static void listTransaction(){

        System.out.println("List of All Transactions: ");
        printArray(transactions);
    }
    public static void printArray (ArrayList<Transaction> transactions){
        System.out.println("-----------------------------------------------------------------------");
        for(Transaction t : transactions ){
            // Print each transaction in a formatted manner
            System.out.printf(
                    "%-12s %-10s %-30s %-15s %10s%n",
                    t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
            );
        }
    }
    public static ArrayList<Transaction> getYearToDateFromFullLedger(ArrayList<Transaction> fullLedger){
        //loop through all transaction (fullLedger) create a new arraylist with only dates that meet the correct criteria...
        return null;
    }
    public static ArrayList<Transaction> getDepositsFromFullLedger(ArrayList<Transaction> fullLedger){
        //loop through all transaction (fullLedger) create a new arraylist with only deposits and return that..
        System.out.println("List of All Deposits  Transactions: ");
        System.out.println("-----------------------------------------------------------------------");

        var depositTransaction = new ArrayList<Transaction>();
        for ( Transaction t : fullLedger){
            if (t.getAmount()>0){
                depositTransaction.add(new Transaction(t.getDate(),t.getTime(),t.getDescription(),t.getVendor(),t.getAmount()));
            }
        }
        return depositTransaction ;
    }
    public static ArrayList<Transaction> getPaymentsFromFullLedger(ArrayList<Transaction> fullLedger){
        //loop through all transaction (fullLedger) create a new arraylist with only Payment and return that..
        System.out.println("List of All Payment  Transactions: ");
        System.out.println("-----------------------------------------------------------------------");

        var paymentTransaction = new ArrayList<Transaction>();
        for ( Transaction t : fullLedger){
            if (t.getAmount()<0){
                paymentTransaction.add(new Transaction(t.getDate(),t.getTime(),t.getDescription(),t.getVendor(),t.getAmount()));
            }
        }
        return paymentTransaction ;
    }

}