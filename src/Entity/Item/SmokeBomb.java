package Entity.Item;

import java.util.ArrayList;
import Entity.Combatant.Combatant;
import Entity.StatusEffect.SmokeBombEffect;
import Entity.StatusEffect.StatusEffect;
import Entity.Action.TargetType;

public class SmokeBomb extends Item{
    // Constructor
    public SmokeBomb(){
        this.name = "Smoke Bomb";
        this.description = "When used, Enemy attacks do 0 damage in the current turn and the next turn";
    }

    @Override
    public String getMessage(Combatant actor, ArrayList<Combatant> targets, ArrayList<Integer> previousHp) {
        return ": Enemy attacks do 0 damage in the current turn and the next turn";
    }

    @Override
    public void use(Combatant actor, ArrayList <Combatant> targets){
        StatusEffect e = new SmokeBombEffect();
        e.applyEffect(actor, targets);
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return TargetType.ALL_ENEMIES;
    }
}