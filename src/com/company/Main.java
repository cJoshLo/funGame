package com.company;


import java.util.Scanner;

import static com.company.Brawl.fight;
import static com.company.GameFunctions.*;

public class Main {



    private static String checkAction(Room currentRoom, String action, Character soldier) {
        //System.out.println("Your action is " + action);
        if (action.equals("left") || action.equals("right") || action.equals("back")) {
            if (currentRoom.left != null && action.equals("left")) {
                System.out.println("You went through the left door");
                return "left";
            } else if (currentRoom.right != null && action.equals("right")) {
                System.out.println("You walk through the right door");
                return "right";
            } else if (currentRoom.previous != null && action.equals("back")) {
                System.out.println("You went back to the previous room");
                return "back";
            } else {
                System.out.println("Room does now have a door on the " + action);
                return "null";
            }
        } else if (action.equals("items")) {
            soldier.checkItems();
            Item check = soldier.getEquippedItem();
            if(check != null) {
                System.out.println(soldier.getEquippedItem().getName() + " is in your hand.");
            }
            return "null";
        } else if (action.equals("instructions")) {
            instructions();
            return "null";
        }else if(action.equals("equip")){
            Scanner scan = new Scanner(System.in);
            String item = scan.nextLine();
            Item weapon = soldier.getItems(item);
            if(weapon != null){
                soldier.setEquippedItem(weapon);
                System.out.println(weapon.getName() + " has been equiped");
            }else{
                System.out.println("You do no have that item");
            }
            return "null";
        }else{
            System.out.println("invalid input");
            return "invalid";
        }
    }


    public static void main(String[] args) {
        //LOAD GAME
        //create items
        Item twig = new Item(5, 1, "twig");
        Item potionD = new Item(0, 5, "Defense Potion");
        Item largeAxe = new Item(10, 2, "Large axe");
        //create monsters
        Spider spider = new Spider(5,2,5);
        //create rooms
        Room start = new Room(null,null);
        Room swordRoom = new Room(null,twig);
        Room spiderMonster = new Room(spider, potionD);
        //link rooms with a tree structure
        start.left = swordRoom;
        start.right = spiderMonster;
        start.previous = null;
        swordRoom.previous = start;
        spiderMonster.previous = start;
        //-----------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------


        //Generating the character role
        Character character = selectRole(); //create the character with correct class
        Room current = start; // create a pointer to the current class the character is in

        System.out.println("Great choice");
        instructions();

        System.out.println("You are in the main room");
        System.out.println("The room you are in is empty but there are two doors one to your right and one to your left");
        //---------------------------------------------------------------------------------------------------------------------------------



        //MAIN GAME LOOP
        Scanner scan = new Scanner(System.in);
        boolean endGame = false;
        while(!endGame) {
            if(current.getMonster() != null){
                System.out.println("Suddenly a " + current.getMonster().getName() + " jumps out and prepares for a fight.");
                boolean won = fight(current.getMonster(), character);
                if(won){
                    System.out.println("You killed the monster, and found a " + current.getItem().getName() + ". The item has been added to your inventory");
                    character.setItems(current.removeItem());
                    current.removeMonster();
                }else{
                    current = start;
                    System.out.println("You put up a good fight but you lost. You have returned to the begining of the map.");
                }
            }else if (current.getItem() != null) {
                System.out.println("You found a " + current.getItem().getName() + ". The item has been added to your inventory");
                character.setItems(current.removeItem());
            }
            System.out.println("What would you like to do?");
            String action = scan.nextLine();
            action.toLowerCase();
            if (action.equals("quit")) {
                endGame = true;
                System.out.println("You have ended the game Goodbye");
                break;
            }
            String checked = checkAction(current, action, character);
            if (checked.equals("left")) {
                current = current.left;
            } else if (checked.equals("right")) {
                current = current.right;
            } else if (checked.equals("back")) {
                current = current.previous;
            }
        }

    }

}

