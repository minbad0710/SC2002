package Entity.Combatant.Player;

import java.util.ArrayList;

import Entity.Combatant.*;
import Entity.Item.*;
import Entity.Action.*;
public abstract class Player extends Combatant{
    ArrayList <Item> inventory = new ArrayList<Item>();
    protected ArrayList<Action> availableActions = new ArrayList<>();
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

    public ArrayList<Action> getAvailableActions() {
        return this.availableActions;
    }

    public void setInventory(ArrayList<Item> inventory){
        this.inventory = inventory; // this one will match with the promptInitialItemSelection() in GameCLI when we connect them in the main
    }
    
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public String getStatsSummary() {
        return String.format("(HP: %d, ATK: %d, DEF: %d, SPD: %d)", this.MaxHP, this.attack, this.defend, this.speed);
    }

    public abstract Player clonePlayer();

    
}