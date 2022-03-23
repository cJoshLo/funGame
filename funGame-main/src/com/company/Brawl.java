package com.company;

import java.util.Scanner;

public class Brawl {
    public static boolean fight(Monster monster, Character soldier){

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
                System.out.println(monster.getName() + " health = " + monster.getTotalHealth());
                return true;
            }else{
                System.out.println("You hit the " + monster.getName() + " for " + attack + ".");
                System.out.println(monster.getName() + " health = " + monster.getTotalHealth());
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
            System.out.println("Your health = " + soldier.getHealth());
            boolean counter = false; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!should add some logic to not ask but just pause if they have no item or no extra item
            while(!counter) {
                System.out.println("Would you like to use any items?");
                Scanner scan = new Scanner(System.in);
                String item = scan.nextLine();
                if (item.equals("items")) {
                    soldier.checkItems();
                }
                if(item.equals("equip")){
                    String item1 = scan.nextLine();
                    Item weapon = soldier.getItems(item1);
                    if(weapon != null) {
                        soldier.setEquippedItem(weapon);
                        System.out.println(weapon.getName() + " has been equiped");
                        hold = soldier.getEquippedItem();
                        attackStats = currentStats(hold, soldier);
                        defenseStats = currentDStats(hold, soldier);
                        System.out.println("Your total attackStat is now " + attackStats + " with a total defenseStat of " + defenseStats + ".");
                    }else{
                        System.out.println("Item does not exist in inventory");
                    }
                }
                if(!item.equals("items") && !item.equals("equip")){
                    counter = true;
                }
            }
        }
        return false;

    }

    private static double currentStats(Item hold, Character soldier){
        if(hold == null){
            return soldier.getAttackPower();
        }else{
            double finalStats = soldier.getAttackPower() + hold.getAdditionalAttack();
            return finalStats;
        }
    }
    private static double currentDStats(Item hold, Character soldier){
        if(hold == null){
            return soldier.getDefensePower();
        }else{
            double finalDStats = soldier.getDefensePower() + hold.getAdditionalDefense();
            return finalDStats;
        }
    }
}
