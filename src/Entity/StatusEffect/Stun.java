package Entity.StatusEffect;

import java.util.ArrayList;

import Entity.Combatant.*;

public class Stun extends StatusEffect{
    final int DURATION = 2;
    public Stun(){
        this.remainingTurns = DURATION;
    }

    public void applyEffect(Combatant actor, ArrayList <Combatant> characters) {
        for (Combatant character: characters){
            if (character == actor){ characters.remove(actor);continue;};
            character.setActive(false);
        };
        super.applyEffect(characters);
        actor.addNewEffect(new Stun());
    }

    @Override
    public void checkTurns(Combatant character){
        if(remainingTurns == 0){
            removeEffect(character);
        }
        else{
            decrementTurns();
        };
        character.reduceCooldown();
    }

    @Override
    public void removeEffect(Combatant character){
        character.setActive(true);
        super.removeEffect(character);
    }

}
