package Entity.Combatant.Player;

import java.util.ArrayList;

import Entity.Combatant.*;
import Entity.Item.*;

public abstract class Player extends Combatant{
    ArrayList <Item> inventory = new ArrayList<Item>();
    public void removeItem(Item item){
        this.inventory.remove(item);
    }
    public Player(int max_hp, int speed, int attack, int defend) {
        super(max_hp, speed, attack, defend);
    }
    
    public void setInventory(ArrayList<Item> inventory){
        this.inventory = inventory; // this one will match with the promptInitialItemSelection() in GameCLI when we connect them in the main
    }
    
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    
}