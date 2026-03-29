package Entity.Item;

import java.util.ArrayList;

import Entity.Combatant.Combatant;
import Entity.StatusEffect.SmokeBombEffect;
import Entity.StatusEffect.StatusEffect;

public class SmokeBomb extends Item{
    public SmokeBomb(){
        this.name = "Smoke Bomb";
        this.description = "When used, Enemy attacks do 0 damage in the current turn and the next turn";
    }
    @Override
    public Item recreate(){
        return new SmokeBomb();
    }

    @Override
    public void use(Combatant actor, ArrayList <Combatant> targets){
        StatusEffect e = new SmokeBombEffect();
        e.applyEffect(targets);
    }
}