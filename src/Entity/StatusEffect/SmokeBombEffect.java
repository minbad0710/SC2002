package Entity.StatusEffect;

import java.util.ArrayList;
import Entity.Combatant.*;

public class SmokeBombEffect extends StatusEffect{
    public SmokeBombEffect(){
        this.remainingTurns = DURATION;
    }
    
    public void applyEffect(ArrayList<Combatant> characters) {
        for (Combatant character: characters){
            character.setAttack(0);
            character.addNewEffect(new SmokeBombEffect());
        };
    }

    @Override
    public void removeEffect(Combatant character){
        character.resetAttack();
        super.removeEffect(character);
    }

}