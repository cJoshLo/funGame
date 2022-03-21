package com.company;

public class Item {
    private double additionalAttack;
    private double additionalDefense;
    private String name;

    public Item(double additionalAttack, double additionalDefense, String name) {
        this.additionalAttack = additionalAttack;
        this.additionalDefense = additionalDefense;
        this.name = name;
    }

    public double getAdditionalAttack() {
        return additionalAttack;
    }

    public void setAdditionalAttack(double additionalAttack) {
        this.additionalAttack = additionalAttack;
    }

    public double getAdditionalDefense() {
        return additionalDefense;
    }

    public void setAdditionalDefense(double additionalDefense) {
        this.additionalDefense = additionalDefense;
    }

    public String getName() {
        return name;
    }

}
