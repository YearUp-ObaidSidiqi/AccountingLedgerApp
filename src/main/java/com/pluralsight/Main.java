package com.pluralsight;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("----------     Welcome to Accounting Ledger Application!     ----------");


    }
    // Top level menu,
    static void appHomeScreen(){

        System.out.println("Add Deposit");
        System.out.println("Make Payment (Debit)");
        System.out.println("Show Ledger");
        System.out.println("Exit form ");
    }

    // Deposit Menu,
    static void appDepositMenu(){
        System.out.println("This is your Deposit Menu: \n");

    }

    //Payment Menu,
    static void appLegerMenu(){
        System.out.println("This is your Ledger Menu: \n");
    }

    //Ledger Menu
    static void ledgerMenu(){
        System.out.println("This is your Ledger Menu: \n");
    }

    //Ledger - Display all Entries
    static void displayAllEntriesLedgers(){
        System.out.println("All the Entries: ");
    }

    // Display only Deposits
    static void displayDepositsLedger(){
        System.out.println("All Deposits: ");
    }

    // Display only Payments
    static void displayPaymentsLedger(){
        System.out.println("All Deposits: ");
    }

    // Display pre-defined  Reports
    static void displayReportsLedger(){
        System.out.println("All Deposits: ");
    }

}