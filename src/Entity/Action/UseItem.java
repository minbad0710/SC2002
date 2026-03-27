package Entity.Action;

import Entity.Item.*;
import Entity.Combatant.*;
import Entity.Combatant.Player.*;
import java.util.ArrayList;

import Control.ItemInteract;

public class UseItem extends Action{
    private Item item;

    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets){
        Player p = (Player) actor;
        /*method to show Item to choose in game CLI */
        this.item = ItemInteract.decideItem(p);
        item.use(actor, targets);
        p.removeItem(item);
    }

    @Override
    public boolean isAvailable(Combatant actor){
        Player p = (Player) actor;
        ArrayList<Item> items = p.getInventory();
        return items.size() > 0;
    }

}