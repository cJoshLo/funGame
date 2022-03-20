package com.company;

public interface Character {
    public String name;
    public Item[] items; //an array of all the items the character has
    public double attackPower();
    public double defensePower();

}
