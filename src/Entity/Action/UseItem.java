package Entity.Action;

import Entity.Item.*;
import Entity.Combatant.*;
import Entity.Combatant.Player.*;
import java.util.ArrayList;

public class UseItem extends Action{
    private Item item; // the selected item for using
    // Constructor
    public UseItem(){
        this.name = "Use Item";
        this.description = "Use item in the inventory list";
    }
    // set the item = the selected item in game CLI
    public void setItem(Item item){
        this.item = item;
    }

    @Override
    public void execute(Combatant actor){
        if (actor instanceof Player){
            Player p = (Player) actor;
            this.item.use(actor, targets); // execute the "use" function in Item class
            p.removeItem(item); // after using this item, removing it from the inventory list of player
            this.resultMessage = p.getName() + " uses " + item.getName() + ". " + item.getDescription();
        }
    }

    @Override
    public boolean isAvailable(Combatant actor){
        Player p = (Player) actor;
        ArrayList<Item> items = p.getInventory();
        return items.size() > 0; // the UseItem action can only be executeed if the player still has item to use
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return this.item.getTargetType(actor);
    }
}