package Entity.Item;

import Entity.Combatant.Combatant;
import java.util.ArrayList;
import Entity.Action.TargetType;
public class Potion extends Item{
    // Constructor
    public Potion(){
        this.name ="Potion";
        this.description = "Heal 100HP";
    }
    
    @Override
    public void use(Combatant actor, ArrayList <Combatant> targets){
        actor.heal(100);
    }

    @Override
    public String getMessage(Combatant actor, ArrayList<Combatant> targets, ArrayList<Integer> previousHp) {
        return ": HP: " + previousHp.get(0) + " -> " + actor.getHp() + " (+100) | Potion consumed"; // the message after using this item, which is the hp change of player
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return TargetType.SELF;
    }
}
