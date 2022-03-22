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


    public double getAdditionalDefense() {
        return additionalDefense;
    }


    public String getName() {
        return name;
    }

}
