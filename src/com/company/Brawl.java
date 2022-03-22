package com.company;

import java.util.Scanner;

public class Brawl {
    public static boolean fight(Monster monster, Soldier soldier){
        Item hold = soldier.getEquippedItem();
        double attackStats = currentStats(hold, soldier);
        double defenseStats = currentDStats(hold, soldier);
        System.out.println("The " + monster.getName() + " has a health of " + monster.getTotalHealth() + ".");
        System.out.println("You have a total attackStat of " + attackStats + " and a total defenseStat of " + defenseStats + ".");
        System.out.println("");
        while(soldier.getHealth() > 0){

            double attack = (int)(Math.random() * attackStats);
            if(attack < monster.getDefenseStrength()){
                attack  = 0;
            }else{
                attack = attack - monster.getDefenseStrength();
            }
            double newHealth = monster.getTotalHealth() - attack;
            monster.setTotalHealth(newHealth);
            if(monster.getTotalHealth() <= 0){
                System.out.println("You hit the " + monster.getName() + " for " + attack + ".");
                System.out.println(monster.getName() + " has " + monster.getTotalHealth() + " health left");
                return true;
            }else{
                System.out.println("You hit the " + monster.getName() + " for " + attack + ".");
                System.out.println(monster.getName() + " has " + monster.getTotalHealth() + " health left");
                System.out.println("");
            }

            double damage = (int)(Math.random()*monster.getAttackStrength());
            if(damage < defenseStats){
                damage  = 0;
            }else{
                damage = damage - defenseStats;
            }
            soldier.setHealth(soldier.getHealth()-damage);
            System.out.println("The " + monster.getName() + " strikes back and hits you for " + damage + ".");
            if(soldier.getHealth() <= 0){
                System.out.println("Your health is 0");
                soldier.setHealth(10);
                return false;
            }
            System.out.println("Your health is now " + soldier.getHealth() + ".");
            System.out.println("Would you like to use any items?");
            Scanner scan = new Scanner(System.in);
            String item = scan.nextLine();
        }
        return false;

    }

    private static double currentStats(Item hold, Soldier soldier){
        if(hold == null){
            return soldier.getAttackPower();
        }else{
            double finalStats = soldier.getAttackPower() + hold.getAdditionalAttack();
            return finalStats;
        }
    }
    private static double currentDStats(Item hold, Soldier soldier){
        if(hold == null){
            return soldier.getDefensePower();
        }else{
            double finalDStats = soldier.getDefensePower() + hold.getAdditionalDefense();
            return finalDStats;
        }
    }
}
