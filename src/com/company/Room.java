package com.company;

public class Room {
    private Monster monster;
    private Item item;

    public Room right;
    public Room left;
    public Room previous;

    public Room(Monster monster, Item item) {
        this.monster = monster;
        this.item = item;
    }



    public boolean itemHere(Item[] items){ //checks to see if the item is still in the room or if it is already in inventory
        for(Item i : items){
            if(i == this.item){
                return false;
            }
        }
        return true;
    }


    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item removeItem(){
        Item hold = this.item;
        this.item = null;
        return hold;
    }

    public void removeMonster(){
        this.monster = null;
    }

}
