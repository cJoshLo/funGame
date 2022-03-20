package com.company;

public class Room {
    private Monster;
    private Item;

    public boolean itemHere(item[] items){ //checks to see if the item is still in the room or if it is already in inventory
        for(auto i : items){
            if(i == this.Item){
                return false;
            }
        }
        return true;
    }

}
