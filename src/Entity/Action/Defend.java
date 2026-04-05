package Entity.Action;

import Entity.Combatant.*;
import Entity.StatusEffect.DefendEffect;

import java.util.ArrayList;

public class Defend extends Action{
    public Defend(){
        this.name = "Defend";
        this.description ="Increase defend by 10 in the current and next round";
    }

    @Override
    public void execute(Combatant actor){
        DefendEffect d = new DefendEffect();
        ArrayList<Combatant> user = new ArrayList<Combatant>();
        user.add(actor);
        d.applyEffect(user);
        this.resultMessage = actor.getName() + " defends and increases defense by 10 for the current and next round.";
    }

    @Override
    public boolean isAvailable(Combatant actor){
        return true; // check stunned in battle engine
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return TargetType.SELF;
    }

    
}