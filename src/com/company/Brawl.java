package com.company;

public class Brawl {
    public static boolean fight(Monster monster, Soldier soldier){
        Item hold = soldier.getItems("sword");
        if(hold != null){
            return true;
        }else{
            return false;
        }
    }
}
