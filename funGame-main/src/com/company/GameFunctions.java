package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameFunctions {

    //-----------Game Instructions------------
    public static void instructions(){
        System.out.println("MOVEMENT");
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
        System.out.println("character stats = 'character'");
        System.out.println("end game = 'quit' or beating the game.");
        System.out.println("instructions =  'instructions'.");
        System.out.println("");
        System.out.println("");
    }


    //-----------Selecting role--------------------
    public static Character selectRole(){
        int characterType = 0;
        List<String> characters = new ArrayList<>();//adding all classes to a list
        characters.add("Soldier");
        characters.add("Bruiser");
        characters.add("Grunt");
        characters.add("Mage");

        System.out.println("Welcome to the game, first select a name");
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        String userName = scan.nextLine();  // Read user input
        System.out.println("Great to meet you " + userName);
        boolean check = false;
        while(!check) {
            System.out.println("Please select a class");
            for(int i = 0; i < characters.size(); i++){//printing out all of the classes available
                System.out.println((i+1) + ": " + characters.get(i));
            }
            characterType = scan.nextInt();
            scan.nextLine();
            check = scanCheck(characterType, characters.size()+1,0);
        }
        switch (characterType){//initializing the class the user picked
            case 1:
                Soldier soldier = new Soldier(userName);
                return soldier;
            case 2:
                Bruiser bruiser = new Bruiser(userName);
                return bruiser;
            case 3:
                Grunt grunt = new Grunt(userName);
                return grunt;
            case 4:
                Mage mage = new Mage(userName);
                return mage;
            default:
                Soldier soldier1 = new Soldier(userName);
                return soldier1;
        }
    }

    //checking user input is within scope
    private static boolean scanCheck(int num, int topBound, int bottomBound){
        if(num > bottomBound && num < topBound){
            return true;
        }else{
            return false;
        }
    }

}
