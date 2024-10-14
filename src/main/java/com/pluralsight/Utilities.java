package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utilities {

    static Scanner scanner = new Scanner(System.in);

    public static String PromptForString(String prompt){
        System.out.print(prompt);
        String input = scanner.nextLine().toUpperCase();
        return input;
    }

    public static LocalDate PromptForLocalDate(String prompt){
        System.out.print(prompt);
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(input,formatter);


        return localDate;
    }
    public static LocalTime PromptForLocalTime(String prompt){
        System.out.print(prompt);
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss, a");
        LocalTime localTime = LocalTime.parse(input,formatter);


        return localTime;
    }


    public static char PromptForChar(String prompt){
        System.out.print(prompt);
        String input = scanner.nextLine().trim().toUpperCase();
        char chr = input.charAt(0);
        return chr;
    }





    public static String PromptForString(){
        return scanner.nextLine();
    }

    public static short PromptForShort(String prompt){
        System.out.print(prompt);
        short userinput = scanner.nextShort();
        scanner.nextLine();
        return  userinput;
    }

    public static byte PromptForByte(String prompt){
        System.out.print(prompt);
        byte userinput =scanner.nextByte();
        return userinput;
    }

    public static int PromptForInt(String prompt){
        System.out.print(prompt);
        String userInputs = scanner.nextLine();
        int userinput =Integer.parseInt(userInputs);
        scanner.nextLine();
        return userinput;
    }

    public static double PromptForDouble(String prompt){
        System.out.print(prompt);
        String userInputs = scanner.nextLine();
        double userinput = Double.parseDouble(userInputs);
        return userinput;
    }

public static boolean PromptForYesNo(String prompt){
    System.out.print(prompt + " ( Y for Yes, N for No ) ?");
    String userinput = scanner.nextLine();

    return
            (
                    userinput.equalsIgnoreCase("Y")
                            ||
                            userinput.equalsIgnoreCase("YES")
            );

}
}