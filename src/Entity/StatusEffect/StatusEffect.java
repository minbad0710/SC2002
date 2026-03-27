package Entity.StatusEffect;

import Entity.Combatant.*;
import java.util.ArrayList;

public class StatusEffect {
    final int DURATION = 1;
    int remainingTurns;
    public StatusEffect(){
        this.remainingTurns = DURATION;
    }
    public void applyEffect(ArrayList <Combatant> characters){
        for (Combatant character: characters){
            character.addNewEffect(this);
        };
    }
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

