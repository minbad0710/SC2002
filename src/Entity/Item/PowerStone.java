package Entity.Item;

import java.util.ArrayList;
import Entity.Combatant.Combatant;
import Entity.SpecialSkill.SpecialSkill;
import Entity.Action.TargetType;
public class PowerStone extends Item{
    // Constructor
    public PowerStone(){
        this.name= "Power Stone";
        this.description = "Activate Special skill without affecting cooldown";
    }

    @Override
    public void use(Combatant actor, ArrayList<Combatant> targets){
        int cooldown = actor.getCooldown();
        SpecialSkill s = actor.getSpecialSkill();
        s.execute(actor, targets); // special skill when execute will change the cool down
        actor.setCooldown(cooldown); // change the cool down again
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return actor.getSpecialSkill().getTargetType();
    }

}
