package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.Brawl.fight;
import static com.company.GameFunctions.*;

public class Main {


    //------------Interpret User Input-----------------------------------------------------------
    private static String checkAction(Room currentRoom, String action, Character soldier, boolean doorLock) {
        action = action.toLowerCase();
        if (action.equals("left") || action.equals("right") || action.equals("back")) { //----------Movement
            if (currentRoom.left != null && action.equals("left")) {//-----------------------Moving Left
                System.out.println("You went through the left door");
                return "left";
            } else if (currentRoom.right != null && action.equals("right")) {//-------------------Moving Right
                if(currentRoom.getInteractive().equals("keyhole Room") && doorLock){
                    System.out.println("The door is locked you can not go through");
                    return "null";
                }
                System.out.println("You walk through the right door");
                return "right";
            } else if (currentRoom.previous != null && action.equals("back")) {//-----------------Moving Back
                System.out.println("You went back to the previous room");
                return "back";
            } else {
                System.out.println("Room does now have a door on the " + action);
                return "null";
            }
        } else if (action.equals("items")) {//----------------------------------------------------List Items
            soldier.checkItems();
            Item check = soldier.getEquippedItem();
            if(check != null) {
                System.out.println(soldier.getEquippedItem().getName() + " is in your hand.");
            }
            return "null";
        } else if (action.equals("instructions")) {//--------------------------------------------Game instructions
            instructions();
            return "null";
        }else if(action.equals("equip")){//------------------------------------------------------Equip Item
            Scanner scan = new Scanner(System.in);
            String item = scan.nextLine();
            item = item.toLowerCase();
            item = item.trim();
            Item weapon = soldier.getItems(item);
            if(weapon != null){
                soldier.setEquippedItem(weapon);
                if(weapon.getName().equals("defense spell") && soldier.getCharacterClass().equals("Mage")){
                    soldier.setDefensePower(8);
                    System.out.println("Your defense power drastically increased!");
                    soldier.removeItem(weapon.getName());
                }else if(weapon.getName().equals("defense spell")){
                    soldier.setDefensePower(soldier.getDefensePower() + 2);
                    System.out.println("Your defense power increased by 2.");
                    soldier.removeItem(weapon.getName());
                }else{
                    System.out.println(weapon.getName() + " has been equipped");
                }
            }else{
                System.out.println("You do no have that item");
            }
            return "null";
        }else if(action.equals("stats")){ //-------------------------------------------------Item stats
            Scanner scan = new Scanner(System.in);
            String item = scan.nextLine();
            item = item.toLowerCase();
            item = item.trim();
            Item weapon = soldier.getItems(item);
            if(weapon != null){
                System.out.println("The stats of " + weapon.getName());
                System.out.println("Attack Power: " + weapon.getAdditionalAttack());
                System.out.println("Defense Power: " + weapon.getAdditionalDefense());
            }else{
                System.out.println("You do no have that item");
            }
        }else if(action.equals("current")){//-----------------------------------------------Current room name
            System.out.println("You are in the " + currentRoom.getInteractive());
        }else if(action.equals("doors")){//-------------------------------------------------Current move options
            List<String> doors = new ArrayList<>();
            doors.add("Previous Door");
            if(currentRoom.left != null){
                doors.add("Left Door");
            }
            if(currentRoom.right != null){
                doors.add("Right Door");
            }
            System.out.println("You can move through: ");
            for (int i = 0; i < doors.size(); i++){
                System.out.println(doors.get(i));
            }
        }else if(action.equals("character")){//-----------------------------------------Character stats
            System.out.println("You are a " + soldier.getCharacterClass());
            System.out.println("Name: " + soldier.getName());
            System.out.println("Health :" + soldier.getHealth());
            System.out.println("Attack power: " + soldier.getAttackPower());
            System.out.println("Defense power: " + soldier.getDefensePower());
        }
        return "invalid";
    }


    public static void main(String[] args) {

        //------------LOAD GAME-----------------------------------------

        //create items
        Item twig = new Item(5, 1, "twig");
        Item potionD = new Item(0, 5, "defense spell");
        Item largeAxe = new Item(10, 2, "large axe");
        Item key = new Item(0,0,"key");
        Item trophy = new Item(0,0,"trophy");

        //create monsters
        Spider spider = new Spider(5,2,5);
        Monster giant = new Giant(8,3,10);

        //create rooms
        boolean doorLock = true;//locks room specialItemRoom
        Room start = new Room(null,null, "start Room");
        Room twigRoom = new Room(null,twig, "twig Room");
        Room spiderMonster = new Room(spider, potionD, "Spider Room");
        Room giantMonster = new Room(giant, null, "Giant Room");
        Room keyRoom = new Room(null, key, "key Room");
        Room keyHoleRoom = new Room(null, null, "keyhole Room");
        Room emptySpace1 = new Room(null, null, "warning Room");
        Room specialItemRoom = new Room(null, largeAxe, "Super Item Room");
        Room beatTheGameRoom = new Room(null, trophy, "won Room");

        //link rooms with a tree structure
        start.left = twigRoom;
        start.right = spiderMonster;
        start.previous = null;
        twigRoom.previous = start;
        twigRoom.left = keyHoleRoom;
        keyHoleRoom.previous = twigRoom;
        keyHoleRoom.right = specialItemRoom;
        specialItemRoom.right = giantMonster;
        specialItemRoom.previous = keyHoleRoom;
        spiderMonster.previous = start;
        spiderMonster.right = keyRoom;
        keyRoom.previous = spiderMonster;
        spiderMonster.left = emptySpace1;
        emptySpace1.left = giantMonster;
        emptySpace1.previous = spiderMonster;
        giantMonster.right = beatTheGameRoom;


        //-----------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------


        //---------Generating the character role--------------------------------
        Character character = selectRole(); //create the character with correct class
        Room current = start; // create a pointer to the current class the character is in

        System.out.println("Great choice");
        System.out.println("You can move by typing 'left' ,'right', or 'back'.");
        System.out.println("You can get a full list of instructions by typing 'instructions'.");
        System.out.println("");
        System.out.println("You are in the main room");
        System.out.println("The room you are in is empty but there are two doors one to your right and one to your left");
        //---------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------


        //-----------MAIN GAME LOOP----------------------------------------------
        Scanner scan = new Scanner(System.in);
        boolean endGame = false;
        while(!endGame) {
            if(current.getMonster() != null){//------------------------------------------------------------------------------Check if there is a monster in the room
                System.out.println("Suddenly a " + current.getMonster().getName() + " jumps out and prepares for a fight.");
                boolean won = fight(current.getMonster(), character);
                if(won){
                    if(current.getItem() != null) {
                        System.out.println("You killed the monster, and found a " + current.getItem().getName() + ". The item has been added to your inventory");
                    }else{
                        System.out.println("You killed the monster");
                    }
                    character.setItems(current.removeItem());
                    current.removeMonster();
                }else{
                    current = start;
                    System.out.println("You put up a good fight but you lost. You have returned to the beginning of the map.");
                }
            }else if (current.getItem() != null) {//-----------------------------------------------------------------------------Check if there is an item in the room
                System.out.println("You found a " + current.getItem().getName() + ". The item has been added to your inventory");
                character.setItems(current.removeItem());
            }

            if(current.getInteractive() != null){//-----------------------------------------------------------------------------Check if there is an interactive in the room
                String interactive = current.getInteractive();
                switch (interactive){
                    case "keyhole Room":
                        System.out.println("The room appears to be empty but there is a keyhole in the side of the wall");
                        if(character.getEquippedItem() == key){
                            System.out.println("You use the key in your hand to unlock the door");
                            doorLock = false;
                        }
                        break;
                    case "warning Room":
                        System.out.println("There is no turning back, once you enter. I hope you have prepared yourself");
                        break;
                    case "Super Item Room":
                        System.out.println("Something large lay ahead, are you ready?");
                        break;
                    case "won Room":
                        System.out.println("Congratulations you won the game!");
                        endGame = true;
                        break;
                }
            }
            if(endGame) return;
            System.out.println("What would you like to do?");
            String action = scan.nextLine();
            action = action.toLowerCase();
            action = action.trim();//removes the leading and trailing spaces
            if (action.equals("quit")) {
                endGame = true;
                System.out.println("You have ended the game Goodbye");
                break;
            }

            // User input check
            String checked = checkAction(current, action, character, doorLock);


            if (checked.equals("left")) {
                System.out.println(current.getInteractive()+ " -->");
                current = current.left;
                System.out.println("--> " +current.getInteractive());
            } else if (checked.equals("right")) {
                System.out.println(current.getInteractive()+ " -->");
                current = current.right;
                System.out.println("--> " + current.getInteractive());
            } else if (checked.equals("back")) {
                System.out.println(current.getInteractive() + " -->");
                current = current.previous;
                System.out.println("--> " + current.getInteractive());
            }
        }

    }

}

