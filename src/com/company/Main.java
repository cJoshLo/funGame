package com.company;


import java.util.Scanner;

import static com.company.Brawl.fight;

public class Main {

    private static boolean scanCheck(int num, int topBound, int bottomBound){
        if(num > bottomBound && num < topBound){
            return true;
        }else{
            return false;
        }
    }

    private static void instructions(){
        System.out.println("You can check your inventory by typing 'items' you can move by typing 'left' ,'right', or 'back'.");
        System.out.println("You can equip an item by typing 'equip' then pressing enter, then type the name of the item and press enter again.");
        System.out.println("You can end the game by typing 'quit' or beating the game.");
        System.out.println("You can always bring these instructions back by typing 'instructions'.");
        System.out.println("");
        System.out.println("");
    }

    private static String checkAction(Room currentRoom, String action, Soldier soldier) {
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
            System.out.println(soldier.getEquippedItem().getName() + " is in your hand.");
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


        String[] characters = new String[1];
        characters[0] = "soldier";
        //create items
	    Item twig = new Item(5, 1, "twig");
        Item sword = new Item(10, 5, "sword");
        Item largeAxe = new Item(10, 2, "Large axe");
        //create monsters
        Spider spider = new Spider(5,2,5);
        //create rooms
        Room start = new Room(null,null);
        Room swordRoom = new Room(null,twig);
        Room spiderMonster = new Room(spider, sword);
        //link rooms with a tree structure
        start.left = swordRoom;
        start.right = spiderMonster;
        start.previous = null;
        swordRoom.previous = start;
        spiderMonster.previous = start;

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
            int characterType = scan.nextInt();
            scan.nextLine();
            check = scanCheck(characterType, characters.length+1,0);
        }


        System.out.println("Great choice");
        Soldier soldier = new Soldier(userName); //will need to figure out how to pick the correct class when there are more

        instructions();

        System.out.println("You are in the main room");
        System.out.println("The room you are in is empty but there are two doors one to your right and on to your left");
        Room current = start;




        boolean endGame = false;
        while(!endGame) {
            if(current.getMonster() != null){
                System.out.println("Suddenly a " + current.getMonster().getName() + " jumps out and prepares for a fight.");
                boolean won = fight(current.getMonster(), soldier);
                if(won){
                    System.out.println("You killed the monster, and found a " + current.getItem().getName() + ". The item has been added to your inventory");
                    soldier.setItems(current.removeItem());
                    current.removeMonster();
                }else{
                    current = start;
                    System.out.println("You put up a good fight but you lost. You have returned to the begining of the map.");
                }
            }else if (current.getItem() != null) {
                System.out.println("You found a " + current.getItem().getName() + ". The item has been added to your inventory");
                soldier.setItems(current.removeItem());
            }
            System.out.println("What would you like to do?");
            String action = scan.nextLine();
            action.toLowerCase();
            if (action.equals("quit")) {
                endGame = true;
                check = true;
                System.out.println("You have ended the game Goodbye");
                break;
            }
            String checked = checkAction(current, action, soldier);
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

