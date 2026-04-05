package Entity.Item;

import Entity.Combatant.Combatant;
import java.util.ArrayList;
import Entity.Action.TargetType;
public class Potion extends Item{
    public Potion(){
        this.name ="Potion";
        this.description = "Heal 100HP";
    }
    

    @Override
    public void use(Combatant actor, ArrayList <Combatant> targets){
        actor.heal(100);
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return TargetType.SELF;
    }
}
