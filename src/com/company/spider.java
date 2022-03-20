package com.company;

public class spider implements Monster{
    private double attackStrength = 33;
    private double defenseStrength = 15;
    private double totalHealth = 100;

    @Override
    public double attack(double userDefense) {
        double total = attackStrength - userDefense;
        return total;
    }

    @Override
    public double defense(double userAttack) {
        double total = defenseStrength - userAttack;
        this.totalHealth = this.totalHealth - total;
        if(this.totalHealth <= 0){
            double res = -1;
            return res;
        }else{
            return this.totalHealth;
        }
    }


}
