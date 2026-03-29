package Entity.Action;

import java.util.ArrayList;

import Entity.Combatant.Combatant;

public class BasicAttack extends Action {
    public BasicAttack(){
        this.name = "Basic Attack";
        this.description ="Attack targeted enemy";
    }

    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets){
        for (Combatant target: targets){
            if (target == actor){continue;};
            target.takeDamage(actor.getAttack());
        };
    }

    @Override
    public boolean isAvailable(Combatant actor){
        return true; // check stunned in battle engine
    }

}
