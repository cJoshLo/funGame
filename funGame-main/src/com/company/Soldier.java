package com.company;

import java.util.ArrayList;
import java.util.List;

public class Soldier implements Character{
    private String name = "John";
    private List<Item> items = new ArrayList<Item>();

    private Item equippedItem = null;
    private double attackPower = 4;
    private double defensePower = 1;
    private double health = 10;


    public Soldier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void checkItems(){
        System.out.print("You have ");
        if(this.items.isEmpty()){
            System.out.println("no items");
            return;
        }
        System.out.println("");
        for(int i = 0; i < this.items.size(); i++){
            System.out.println(items.get(i).getName());
        }
    }

    public Item getItems(String name) {
        for(int i = 0; i < this.items.size(); i++){
            if(this.items.get(i).getName().equals(name)){
                return this.items.get(i);
            }
        }
        return null;
    }

    public void setItems(Item items) {
        this.items.add(items);
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