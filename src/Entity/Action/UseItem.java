package Entity.Action;

import Entity.Item.*;
import Entity.Combatant.*;
import Entity.Combatant.Player.*;
import java.util.ArrayList;

public class UseItem extends Action{
    private Item item;
    public UseItem(){
        this.name = "Use Item";
        this.description = "Use item in the inventory list";
    }
    public Item getItem(){
        return this.item;
    }
    public void setItem(Item item){
        this.item = item;
    }

    @Override
    public void execute(Combatant actor){
        if (actor instanceof Player){
            Player p = (Player) actor;
            this.item.use(actor, targets);
            p.removeItem(item);
            this.resultMessage = p.getName() + " uses " + item.getName() + ". " + item.getDescription();
        }
    }

    @Override
    public boolean isAvailable(Combatant actor){
        Player p = (Player) actor;
        ArrayList<Item> items = p.getInventory();
        return items.size() > 0;
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return this.item.getTargetType(actor);
    }
}