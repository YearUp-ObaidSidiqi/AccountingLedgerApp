package com.pluralsight;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("----------     Welcome to Accounting Ledger Application!     ----------");
        System.out.println("-----------------------------------------------------------------------\n");
        appHomeScreen();
        boolean exitApp = true;


        while (exitApp){
        char command = Utilities.PromptForStringAndGetChar("Please Select From: [D], [P], [L], [X] :  ");
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
                System.out.println("*** ERROR! ***  Invalid Selection: Please Select From: [D], [P], [L], [X] : ");

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
        System.out.println("This is your Deposit Menu: \n");
    }

    // Method to make Payment
    static void addPayment(){
        System.out.println("This is your Payment Menu: \n");
    }

    //Method to display Ledger
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