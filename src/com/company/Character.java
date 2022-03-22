package com.company;

import java.util.ArrayList;
import java.util.List;

public interface Character {
    String name = null;
    List<Item> items = new ArrayList<Item>();
    Item equippedItem = null;
    double attackPower = 0;
    double defensePower = 0;
    double health = 0;

    String getName();

    //editing the Items
    void checkItems();//get a list of all in the inventory
    Item getItems(String name); //this is used more internally to get an item being called for in the game
    void setItems(Item items); //used to add an item to the characters inventory

    //getter and setter for attack and defense
    double getAttackPower();
    void setAttackPower(double attackPower);
    double getDefensePower();
    void setDefensePower(double defensePower);

    //equiping items
    Item getEquippedItem();
    void setEquippedItem(Item equippedItem);

    //health functions
    double getHealth();
    void setHealth(double health);

}
