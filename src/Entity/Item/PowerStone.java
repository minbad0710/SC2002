package Entity.Item;

import java.util.ArrayList;

import Entity.Combatant.Combatant;
import Entity.SpecialSkill.SpecialSkill;

public class PowerStone extends Item{
    public PowerStone(){
        this.name= "Power Stone";
        this.description = "Activate Special skill without affecting cooldown";
    }

    @Override
    public void use(Combatant actor, ArrayList<Combatant> targets){
        SpecialSkill s = actor.getSpecialSkill();
        s.execute(actor, targets);
        actor.setCooldown(0);
    }

}
