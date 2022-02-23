package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        System.out.println("Insert directory path, please.");
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        AbstractCache<String, String> cashe = new DirFileCache(userInput);
        while (!"Exit".equals(userInput)) {
            System.out.println("Insert file's name, please.");
            userInput = in.nextLine();
            System.out.println(cashe.get(userInput));
            System.out.println("Do you want continue program? Enter \"Y\" or \"N\".");
            userInput = in.nextLine();
            while (!"Y".equals(userInput) && !"N".equals(userInput)) {
                System.out.println("Make your change, please.");
                userInput = in.nextLine();
            }
            if ("N".equals(userInput)) {
                System.exit(0);
            }
        }
    }
}
