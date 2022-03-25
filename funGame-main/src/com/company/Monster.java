package com.company;

public interface Monster {
    String name = null;
    public String getName();
    public double getAttackStrength();
    public double getDefenseStrength();
    public double getTotalHealth();
    public void setTotalHealth(double totalHealth);

    public double attack(double userDefencse);

    public double defense(double userAttack);
}
