package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

public class TEST {

    static LocalDate currentDate = LocalDate.now();

    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static ArrayList<Transaction> transactions;
    static {
        transactions = getTransactions();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        System.out.println(currentMonth);
        System.out.println(currentYear);
        int currentMonth2 = currentDate.getMonthValue()-1;
        int currentYear2 = currentDate.getYear()-1;
        System.out.println(currentMonth2);
        System.out.println(currentYear2);


            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-------------- This is " + currentDate.getMonth() +" Report section ----------------");
            System.out.println("-----------------------------------------------------------------------\n");
            for(Transaction t : transactions ){
                int transactionMonth = ((t.getDate().getMonthValue())-1);
                int transactionYear = t.getDate().getYear();

                if((transactionMonth)==currentMonth && currentYear==transactionYear) /// the argument!
                    // Print each transaction in a formatted manner
                    System.out.printf(
                            "%-12s %-10s %-30s %-15s %10s%n",
                            t.getDate().format(formatter1), t.getTime().format(formatter2), t.getDescription(), t.getVendor(), t.getAmount()
                    );
            }
            System.out.println("-----------------------------------------------------------------------\n");




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

    public static void printScrollingStars() throws InterruptedException {
        for (int i = 0; i < 40; i++) {
            // Print stars across the screen
            System.out.print("* ");
            Thread.sleep(200);  // Adjust delay for the scrolling effect
        }
        System.out.println("\n\n");
    }
    public static void printCheckerboardTransition() {
        System.out.println("\n");

        for (int i = 0; i < 2; i++) {
            System.out.println("##  ##  ##  ##  ##  ##  ##  ##");
            System.out.println("  ##  ##  ##  ##  ##  ##  ##  ##");
        }

        System.out.println("\n");
    }
    public static void printPyramidTransition() {
        System.out.println("\n");

        // Top of the pyramid
        for (int i = 0; i < 1; i++)
            System.out.println("         *");

        // Mid pyramid
        for (int j = 0; j < 1; j++)
            System.out.println("       *****");

        // Base of the pyramid
        for (int k = 0; k < 1; k++)
            System.out.println("     *********");

        System.out.println("   *************");
        System.out.println(" *****************");

        System.out.println("\n");
    }
    public static void printArrowTransition() {
        System.out.println("\n");

        // Arrowhead
        for (int i = 0; i < 1; i++)
            System.out.println("         *");

        // Arrow body
        for (int j = 0; j < 2; j++)
            System.out.println("       *****");

        // Shaft of the arrow
        for (int k = 0; k < 4; k++)
            System.out.println("***************");

        // Arrow tail
        for (int l = 0; l < 2; l++)
            System.out.println("*************");

        System.out.println("\n");
    }
    public static void printWaveTransition() {
        System.out.println("\n");

        // Print the top wave
        for (int i = 0; i < 2; i++)
            System.out.println("~~~~~~~~~~   ~~~~~~~~~~   ~~~~~~~~~~");

        // Print middle wave
        for (int j = 0; j < 2; j++)
            System.out.println("~~~~~~~~~~~~~~~    ~~~~~~~~~~~~~~~~");

        // Print the bottom wave
        for (int k = 0; k < 2; k++)
            System.out.println("~~~~~~~~~~   ~~~~~~~~~~   ~~~~~~~~~~");

        System.out.println("\n");
    }
    public static void printStarsToTransitionMenu() {
        System.out.println("\n");

        // Top border of stars
        for(int i = 0; i < 2; i++)
            System.out.println("***************************************");

        // Middle section with spaces for "Year Up"
        for(int j = 0; j < 4; j++)
            System.out.println("**********   Y E A R   U P   **********");

        // More stars to complete the block
        for (int k = 0; k < 2; k++)
            System.out.println("***************************************");

        // Additional rows with decorative star patterns
        for(int l = 0; l < 1; l++)
            System.out.println("*********   ****************   ********");

        for(int m = 0; m < 1; m++)
            System.out.println("************   **********   ***********");

        for (int n = 0; n < 1; n++)
            System.out.println("******************    *****************");

        // Bottom border of stars
        for(int o = 0; o < 2; o++)
            System.out.println("***************************************");

        System.out.println("\n");
    }





}