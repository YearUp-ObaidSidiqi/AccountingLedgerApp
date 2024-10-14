package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Test {


    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static ArrayList<Transaction> transactions;
    static {
        transactions = getTransactions();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        double x = 25669;
        x *= -1;
        System.out.println(x);

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
