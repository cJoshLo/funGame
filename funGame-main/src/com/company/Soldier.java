package com.company;

import java.util.ArrayList;
import java.util.List;

public class Soldier implements Character{
    final private String name;
    final private String classType = "Soldier";
    private List<Item> items = new ArrayList<>();

    private Item equippedItem = null;
    private double attackPower = 4;
    private double defensePower = 1;
    private double health = 10;


    //-------------Constructor---------------------
    public Soldier(String name) {
        this.name = name;
    }



    //--------------Item functions-----------------------


    //checks if item list is empty
    public boolean empty(){
        return this.items.isEmpty();
    }

    //checking the total number of items in list
    public int numberOfItems(){
        int total = 0;
        if(empty()){
            return 0;
        }else{
            for(int i = 0; i < items.size(); i++){
                total++;
            }
            return total;
        }
    }

    //returning a list of all items in inventory
    public void checkItems(){
        System.out.print("You have");
        if(this.items.isEmpty()){
            System.out.println(" no items");
            return;
        }
        System.out.println(":");
        for(int i = 0; i < this.items.size(); i++){
            System.out.println(items.get(i).getName());
        }
    }

    //returns the item searched for by name
    public Item getItems(String name) {
        for(int i = 0; i < this.items.size(); i++){
            if(this.items.get(i).getName().equals(name)){
                return this.items.get(i);
            }
        }
        return null;
    }

    //adds an item to the inventory
    public void setItems(Item items) {
        this.items.add(items);
    }

    //remove an item after use
    public void removeItem(String name) {
        Item test = getEquippedItem();
        if(test != null){
            if(test.getName().equals(name)){
                setEquippedItem(null);
            }
        }
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(name)) {
                this.items.remove(i);
            }
        }
    }


    //----------------Getters and Setters ------------------


    public String getCharacterClass(){
        return this.classType;
    }

    public String getName() {
        return name;
    }

    public double getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(double attackPower) {
        this.attackPower = attackPower;
    }

    public double getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(double defensePower) {
        this.defensePower = defensePower;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
