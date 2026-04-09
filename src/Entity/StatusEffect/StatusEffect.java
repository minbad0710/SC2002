package Entity.StatusEffect;

import java.util.ArrayList;

import Entity.Combatant.*;


public abstract class StatusEffect {
    final int DURATION = 1;
    int remainingTurns;

    public StatusEffect(){
        this.remainingTurns = DURATION;
    }
    
    public abstract void applyEffect(Combatant user, ArrayList <Combatant> characters);
    
    public void decrementTurns(){
        remainingTurns--;
    };

    public void checkTurns(Combatant character){
        if(remainingTurns == 0){
            removeEffect(character);
        }
        else{
            decrementTurns();
        };
    }
    public void removeEffect(Combatant character){
        character.removeStatusEffect(this);
    }
}

