package Entity.Combatant.Player;

import java.util.ArrayList;

import Entity.Combatant.*;
import Entity.Item.*;
import Entity.Action.*;
public abstract class Player extends Combatant{
    ArrayList <Item> inventory = new ArrayList<Item>();
    ArrayList <Item> initialItemList = new ArrayList<Item>();
    public void removeItem(Item item){
        this.inventory.remove(item);
    }
    public Player(int max_hp, int speed, int attack, int defend) {
        super(max_hp, speed, attack, defend);
        this.availableActions.add(new BasicAttack());
        this.availableActions.add(new Defend());
        this.availableActions.add(new UseItem());
        this.availableActions.add(new UseSpecialSkill());
    }

    public void setInventory(ArrayList<Item> inventory){
        this.inventory = inventory; 
        ArrayList<Item> tempList = new ArrayList<>(inventory);
        this.initialItemList = new ArrayList<>();
        for (Item item : tempList) {
            if (!initialItemList.contains(item)) {
                initialItemList.add(item);
            }
        }
    }

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    public ArrayList<Item> getinitiallist(){
        return this.initialItemList;
    }

    public abstract Player clonePlayer();

    
}
