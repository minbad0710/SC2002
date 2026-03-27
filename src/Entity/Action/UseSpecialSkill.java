package Entity.Action;

import java.util.ArrayList;

import Entity.Combatant.Combatant;

import Entity.SpecialSkill.SpecialSkill;

public class UseSpecialSkill extends Action{

    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets){
        SpecialSkill s = actor.getSpecialSkill();
        s.execute(actor, targets);
    }
    
    @Override
    public boolean isAvailable(Combatant actor){
        return actor.getCooldown()==0;
    }
}
