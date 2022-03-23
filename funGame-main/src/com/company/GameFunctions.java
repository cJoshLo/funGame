package com.company;

import java.util.Scanner;

public class GameFunctions {

    public static void instructions(){
        System.out.println("MOVMENT");
        System.out.println("you can move by typing 'left' ,'right', or 'back'.");
        System.out.println("");
        System.out.println("USING ITEMS");
        System.out.println("check items in inventory = 'items'");
        System.out.println("equip an item = 'equip' + enter, then the name of the item.");
        System.out.println("check item stats = 'stats' + enter, then the name of the item.");
        System.out.println("");
        System.out.println("LOCATION");
        System.out.println("current room name = 'current'");
        System.out.println("available movements =  'doors'");
        System.out.println("");
        System.out.println("OTHER");
        System.out.println("end game = 'quit' or beating the game.");
        System.out.println("instructions =  'instructions'.");
        System.out.println("");
        System.out.println("");
    }

    public static Character selectRole(){
        int characterType = 0;
        String[] characters = new String[2];
        characters[0] = "Soldier";
        characters[1] = "Bruisor";

        System.out.println("Welcome to the game, first select a name");
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        String userName = scan.nextLine();  // Read user input
        System.out.println("Great to meet you " + userName);
        boolean check = false;
        while(!check) {
            System.out.println("Please select a class");
            for(int i = 0; i < characters.length; i++){
                System.out.println((i+1) + ": " + characters[i]);
            }
            characterType = scan.nextInt();
            scan.nextLine();
            check = scanCheck(characterType, characters.length+1,0);
        }
        switch (characterType){
            case 1:
                Soldier soldier = new Soldier(userName);
                return soldier;
            case 2:
                Bruiser bruiser = new Bruiser(userName);
                return bruiser;
            default:
                Soldier soldier1 = new Soldier(userName);
                return soldier1;
        }
    }

    private static boolean scanCheck(int num, int topBound, int bottomBound){
        if(num > bottomBound && num < topBound){
            return true;
        }else{
            return false;
        }
    }

}
