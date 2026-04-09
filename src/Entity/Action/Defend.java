package Entity.Action;

import Entity.Combatant.*;
import Entity.StatusEffect.DefendEffect;

public class Defend extends Action{
    //Constructor
    public Defend(){
        this.name = "Defend";
        this.description = "Increase defend by 10 in the current and next round";
    }

    @Override
    public void execute(Combatant actor){
        DefendEffect d = new DefendEffect();
        d.applyEffect(actor, targets);
        this.resultMessage = actor.getName() + " defends and increases defense by 10 for the current and next round.";
    }

    @Override
    public boolean isAvailable(Combatant actor){
        return true; // Combatants can always do defend unless they are not active
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return TargetType.SELF;
    }

}