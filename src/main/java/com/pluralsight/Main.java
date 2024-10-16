package com.pluralsight;

import java.io.*;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Main {

    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    static LocalDate currentDate = LocalDate.now();
    static int currentMonth = currentDate.getMonthValue();
    static int currentYear = currentDate.getYear();
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
        appHomeScreen();
    }

    // Top level menu,
    static void appHomeScreen() {
            boolean exitApp = true;
            while (exitApp) {
                String header = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                          Welcome to Accounting Ledger Application!                       │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │ This is your Home screen menu please select from the following:                          │                                      │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    │  D)      Add Deposit                                                                     │
                    │  P)      Make Payment (Debit)                                                            │
                    │  L)      Show Ledger                                                                     │
                    │  X)      Exit Application                                                                │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
                System.out.println(header);
                String prompt = "Please Type and Enter From: [D], [P], [L], [X] ";
                char command = Utilities.PromptForChar(prompt);
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
                        String thankYouMessage = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                                Thank you for choosing Us!!                               │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                            """;
                        System.out.println(thankYouMessage);
                        exitApp = false;
                        break;
                    default:
                        String error = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │*** ERROR! ***  Invalid Selection: Please Type and Enter From: [D], [P], [L], [X] :       │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                            """;
                        System.out.println(error);
                }
            }
        }
    // Method to add Deposit
    static void addDeposit() {
        boolean exitApp = true;
        while (exitApp) {
            String header = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                                 This is Your Deposit Menu                                │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │ Please select from the following:                                                        │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    │  N)      Add a recent deposit                                                            │
                    │  O)      Add an older deposit                                                            │
                    │  X)      To return to Main Menu                                                          │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
            System.out.println(header);
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
                    String error = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │*** ERROR! ***  Invalid Selection: Please Type and Enter From: [N], [O], [X] :            │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                            """;
                    System.out.println(error);


            }
        }
    }
    // Method to make Payment
    static void addPayment() {

        boolean exitApp = true;
        while (exitApp) {
            String header = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                                 This is Your Payment Menu                                │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │ Please select from the following:                                                        │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    │  N)      Add a recent Payment                                                            │
                    │  O)      Add an older Payment                                                            │
                    │  X)      To return to Main Menu                                                          │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
            System.out.println(header);
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
                    String error = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │*** ERROR! ***  Invalid Selection: Please Type and Enter From: [D], [P], [L], [X] :       │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                            """;
                    System.out.println(error);

            }
        }
    }

    // Payments and deposit sub-methods
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

    // Method to show Ledger
    static void ledgerMenu() {

        boolean exitApp = true;
        while (exitApp) {
            String header = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                             This is Your Ledger Menu                                     │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │Please select from the following:                                                         │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    │  A)      View All Transactions                                                           │
                    │  D)      View Deposits                                                                   │
                    │  P)      View Payments                                                                   │
                    │  R)      Generate Reports                                                                │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
            System.out.println(header);

            char command = Utilities.PromptForChar("Please Type and Enter From: [A] | [D] | [P] | [R]   ");
            switch (command) {
                case 'A':
                    displayAllEntriesLedgers();
                    break;
                case 'D':
                    displayDepositsLedger();
                    break;
                case 'P':
                    displayPaymentsLedger();
                    break;
                case 'R':
                     boolean shouldExitToHome = displayReportsLedger();
                     if(shouldExitToHome){
                         return;
                     }
                    break;
                case 'X':
                    exitApp = false;
                    break;
                default:
                    String error = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │*** ERROR! ***  Invalid Selection: Please Type and Enter From: [A], [D], [P], [R], [X] :  │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                            """;
                    System.out.println(error);
            }
        }
    }
    //Ledger - Display all Entries
    static void displayAllEntriesLedgers(){
        String header = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                                 All the Entries                                          │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
        System.out.println(header);

        listTransaction();
    }
    // Display only Deposits
    static void displayDepositsLedger(){
        String header = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                                 All the Deposits                                         │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
        System.out.println(header);
        printArray(getDepositsFromFullLedger(transactions));
    }
    // Display only Payments
    static void displayPaymentsLedger(){
        String header = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                                 All the Payments                                         │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
        System.out.println(header);
        printArray(getPaymentsFromFullLedger(transactions));
    }

    // Display pre-defined Reports menu /// WOP
    static boolean displayReportsLedger() {
        while (true) {
            String header = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                     This is your pre-defined Report section                              │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                         Please select from the following:                                │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    │  1)      This Month's Transactions                                                       │
                    │  2)      Last Month's Transactions                                                       │
                    │  3)      This Year's Transactions                                                        │
                    │  4)      Last Year's Transactions                                                        │
                    │  5)      Find Transactions by Vendor                                                     │
                    │  0)      Return to Report Menu                                                           │
                    │  H)      Return to Home Screen                                                           │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
            System.out.println(header);
            char command = Utilities.PromptForChar("Please Type and Enter From: [1] | [2] | [3] | [4] | [5] | [0] | [H]  ");
            switch (command) {

                case '1':
                    displayThisMonthTransaction();
                    break;
                case '2':
                    displayPreviousMonthTransaction();
                    break;
                case '3':
                    displayThisYearTransaction();
                    break;
                case '4':
                    displayPreviousYearTransaction();
                    break;
                case '5':
                    String vendors = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │                                 These are your major vendors:                            │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    │                                                                                          │
                    │  ┌─────────────────────────────────────────────────────────────────────────────────────┐ │
                    │  |  Restaurant      |  Travel         |  Trading        |  Maintenance    |  Bonds     │ │
                    │  |  Stocks          |  Consultation    |  Salary         |  Amazon FBA     |  Ads      │ │
                    │  └─────────────────────────────────────────────────────────────────────────────────────┘ │
                    │                                                                                          │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                    """;
                    System.out.println(vendors);
                    String x =Utilities.PromptForString("Please Enter the Vendor:  ");
                    displayTransactionByVendor(x);
                    ///  Find Transactions by Vendor
                    break;
                case '0':
                    return false; //just exit this menu to the parent.
                case 'H':
                    return true; // exit all the way to home
                default:
                    String error = """
                    ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                    │*** ERROR! *** Please type and enter from: [1], [2], [3], [4], [5], [4], [5], [0], [H]    │
                    └──────────────────────────────────────────────────────────────────────────────────────────┘
                            """;
                    System.out.println(error);
            }
        }
    }

    /* static void test(){
         double  totalOfDeposits = getAmountTotal((getDepositsFromFullLedger(transactions)));
         double totalOfPayments = -getAmountTotal((getPaymentsFromFullLedger(transactions)));
         double totalCurrentBalance = getAmountTotal(transactions);
     }*/
    static void displayThisMonthTransaction(){
        String reportHeader = """
                        ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                        │                              This is %s - %d Reports                              │
                        └──────────────────────────────────────────────────────────────────────────────────────────┘
                        """.formatted(currentDate.getMonth().toString().toLowerCase(), currentDate.getYear());

        System.out.println(reportHeader);

        for(Transaction t : transactions ){
            int transactionMonth = t.getDate().getMonthValue();
            int transactionYear = t.getDate().getYear();

            if(currentMonth==transactionMonth && currentYear==transactionYear) /// the argument!
                // Print each transaction in a formatted manner
                System.out.printf(
                        "%-12s %-10s %-30s %-15s %10s%n",
                        t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
                );
        }
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────\n");
    }
    static ArrayList<Transaction> getThisMonthTransactions(ArrayList<Transaction> transactions){
        ArrayList<Transaction> filteredResults = new ArrayList<Transaction>();
        //loop through all transactions and populate filtered results and then
        //return filtered results;
        return null;
    }
    static void displayPreviousMonthTransaction(){
       /* System.out.println("-----------------------------------------------------------------------");
        System.out.println("-------------- This is " + currentDate.minusMonths(1).getMonth().toString().toString().toLowerCase() + " - "+currentDate.getYear() +" Reports ----------------");
        System.out.println("-----------------------------------------------------------------------\n");*/
        String reportHeader = """
                        ┌──────────────────────────────────────────────────────────────────────────────────────────┐
                        │                              This is %s - %d Reports                            │
                        └──────────────────────────────────────────────────────────────────────────────────────────┘
                        """.formatted(currentDate.minusMonths(1).getMonth().toString().toLowerCase(), currentDate.getYear());

        System.out.println(reportHeader);

        currentMonth--;
        for(Transaction t : transactions ){
            int transactionMonth = ((t.getDate().getMonthValue()));
            int transactionYear = t.getDate().getYear();

            if(currentMonth==(transactionMonth) && currentYear==transactionYear) /// the argument!
                // Print each transaction in a formatted manner
                System.out.printf(
                        "%-12s %-10s %-30s %-15s %10s%n",
                        t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
                );
        }
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────\n");
    }
    static void displayThisYearTransaction(){
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-------------- This is " + currentDate.getYear() +" Reports ----------------");
        System.out.println("-----------------------------------------------------------------------\n");
        for(Transaction t : transactions ){
            int transactionYear = t.getDate().getYear();

            if(currentYear==transactionYear) /// the argument!
                // Print each transaction in a formatted manner
                System.out.printf(
                        "%-12s %-10s %-30s %-15s %10s%n",
                        t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
                );
        }
    }
    static void displayPreviousYearTransaction(){
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-------------- This is " + currentDate.minusYears(1).getYear() +" Reports  ----------------");
        System.out.println("-----------------------------------------------------------------------\n");
        currentYear--;
        for(Transaction t : transactions ){
            int transactionYear = (t.getDate().getYear());

            if(currentYear==transactionYear) /// the argument!
                // Print each transaction in a formatted manner
                System.out.printf(
                        "%-12s %-10s %-30s %-15s %10s%n",
                        t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
                );
        }
    }
    /// WOP
                static void displayTransactionByVendor(String vendor){
                    for(Transaction t : transactions ){

                        if(vendor.equalsIgnoreCase(t.getVendor())){ /// the argument!
                            // Print each transaction in a formatted manner
                            System.out.printf(
                                    "%-12s %-10s %-30s %-15s %10s%n",
                                    t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
                            );
                        }
                    }
                }

    public static void listTransaction(){

        System.out.println("List of All Transactions: ");
        printArray(transactions);
    }
    public static void printArray (ArrayList<Transaction> transactions){
       // System.out.println("────────────────────────────────────────────────────────────────────────────────────────────\n");
        for(Transaction t : transactions ){
            // Print each transaction in a formatted manner
            System.out.printf(
                    "%-12s %-10s %-25s %-20s %10s%n",
                    t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
            );
        }
    }


    /* public static double getAmountTotal(ArrayList<Transaction> transactions){
                    //loop through to generate a total amount, return that value.

    }*/
    public static ArrayList<Transaction> getDepositsFromFullLedger(ArrayList<Transaction> fullLedger){
        //loop through all transaction (fullLedger) create a new arraylist with only deposits and return that..
        /*System.out.println("List of All Deposits  Transactions: ");
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
*/
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
        /*System.out.println("List of All Payment  Transactions: ");
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
*/
        var paymentTransaction = new ArrayList<Transaction>();
        for ( Transaction t : fullLedger){
            if (t.getAmount()<0){
                paymentTransaction.add(new Transaction(t.getDate(),t.getTime(),t.getDescription(),t.getVendor(),t.getAmount()));
            }
        }
        return paymentTransaction ;
    }
}