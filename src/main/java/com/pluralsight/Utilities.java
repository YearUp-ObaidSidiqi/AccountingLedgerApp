package com.pluralsight;

import java.util.Scanner;

public class Utilities {

    static Scanner scanner = new Scanner(System.in);

    public static String PromptForString(String prompt){
        System.out.print(prompt);
        String input = scanner.nextLine().toUpperCase();
        return input;
    }

    public static char PromptForStringAndGetChar(String prompt){
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