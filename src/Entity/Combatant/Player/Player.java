package Entity.Combatant.Player;

import java.util.ArrayList;

import Entity.Combatant.*;
import Entity.Item.*;

public abstract class Player extends Combatant{
    ArrayList <Item> inventory = new ArrayList<Item>();
    public void removeItem(Item item){
        this.inventory.remove(item);
    }
    public Player(int max_hp, int speed, int attack, int defend){
        super(max_hp, speed, attack, defend);
        this.inventory.add(new SmokeBomb());
        this.inventory.add(new PowerStone());
    }

    /*decide Action*/

    /*No need */

    
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    
}