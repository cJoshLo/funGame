package com.company;

public class Giant implements Monster{

    private String name = "Jimmy the Giant";
    private double attackStrength = 8;
    private double defenseStrength = 3;
    private double totalHealth = 10;

    public Giant(double attackStrength, double defenseStrength, double totalHealth) {
        this.attackStrength = attackStrength;
        this.defenseStrength = defenseStrength;
        this.totalHealth = totalHealth;
    }

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

    public double getAttackStrength() {
        return attackStrength;
    }

    public void setAttackStrength(double attackStrength) {
        this.attackStrength = attackStrength;
    }

    public double getDefenseStrength() {
        return defenseStrength;
    }

    public void setDefenseStrength(double defenseStrength) {
        this.defenseStrength = defenseStrength;
    }

    public double getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(double totalHealth) {
        this.totalHealth = totalHealth;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}




