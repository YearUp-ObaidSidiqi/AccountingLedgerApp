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

                LocalDate date = LocalDate.parse(tokens[0],formatter1);
                LocalTime time = LocalTime.parse(tokens[1],formatter2);

                transaction.add(new Transaction(date,time,tokens[2], tokens[3],Double.parseDouble(tokens[4])));
            }
            bufferedReader.close();
        } catch (Exception e){
            System.out.println("***ERROR!! ArrayList<Transactions>");
            System.out.println(e.getMessage());
        }
        return transaction;
    }

    public static void main(String[] args) throws IOException {
        var bufferedReader = new BufferedReader(new FileReader("accountingLegerApplicationData.csv"));
       // var bufferWriter = new BufferedWriter (new FileWriter("accountingLegerApplicationData.csv"));



        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("----------     Welcome to Accounting Ledger Application!     ----------");
        System.out.println("-----------------------------------------------------------------------\n");
        appHomeScreen();
        boolean exitApp = true;


        while (exitApp){
        char command = Utilities.PromptForChar("Please Type and Enter From: [D], [P], [L], [X] :  ");
        switch (command){

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
    static void appHomeScreen(){
       // System.out.println("-----------------------------------------------------------------------\n");
        System.out.println("--------------- This is Your Home Screen Please Select ----------------");
        System.out.println("D)      Add Deposit");
        System.out.println("P)      Make Payment (Debit)");
        System.out.println("L)      Show Ledger");
        System.out.println("X)      Exit Application\n");
    }

    // Method to add Deposit
    static void addDeposit(){
        //System.out.println("This is your Deposit Menu: \n");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-------------------- This is Your Deposit Menu ----------------------");
        System.out.println("-----------------------------------------------------------------------\n");
        System.out.println("-------------------- Please Select From The Following ----------------");
        //(today’s date and time will be auto-filled)
        System.out.println("N) Add a recent deposit ");
        //(prompted for the date and time)
        System.out.println("O) Add an older deposit \n");


        char command = Utilities.PromptForChar("Please Type and Enter From: [O] | [N] :  ");

        /*String description = Utilities.PromptForString("Please provide a brief description of the deposit:  ");
        String vendor = Utilities.PromptForString("Please enter the name of the vendor or source:  ");
        double amount = Utilities.PromptForDouble("Please input the deposit amount (e.g., 100.50):");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        transactions.add(new Transaction(date,time,description,vendor,amount));*/
    }

    // Method to make Payment
    static void addPayment(){
        System.out.println("This is your Payment Menu: \n");
    }

    //Method to display Ledger
    static void ledgerMenu(){
        System.out.println("This is your Ledger Menu: \n");
        listTransaction();
    }

    //Ledger - Display all Entries
    static void displayAllEntriesLedgers(){
        System.out.println("All the Entries: ");
       // listTransaction();
    }

    // Display only Deposits
    static void displayDepositsLedger(){
        System.out.println("All Deposits Only: ");
    }

    // Display only Payments
    static void displayPaymentsLedger(){
        System.out.println("All Payments Only: ");
    }

    // Display pre-defined  Reports
    static void displayReportsLedger(){
        System.out.println("All Deposits: ");
    }

    public static void listTransaction(){

        System.out.println("List of All Transactions: ");
        System.out.println("-----------------------------------------------------------------------");
        for(Transaction t : transactions ){
            // Print each transaction in a formatted manner

            System.out.printf("%-12s %-10s %-30s %-15s %10s%n", t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount());
        }
    }

}