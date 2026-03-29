package Entity.Action;

import Entity.Item.*;
import Entity.Combatant.*;
import Entity.Combatant.Player.*;
import java.util.ArrayList;

public class UseItem extends Action{
    private Item item;
    public UseItem(){}
    public void setItem(Item item){
        this.item = item;
    }

    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets){
        Player p = (Player) actor;
        this.item.use(actor, targets);
        p.removeItem(item);
    }

    @Override
    public boolean isAvailable(Combatant actor){
        Player p = (Player) actor;
        ArrayList<Item> items = p.getInventory();
        return items.size() > 0;
    }
}