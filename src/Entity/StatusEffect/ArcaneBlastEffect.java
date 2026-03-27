package Entity.StatusEffect;

import Entity.Combatant.*;
import java.util.ArrayList;

public class ArcaneBlastEffect extends StatusEffect{
    public ArcaneBlastEffect(){
        this.remainingTurns = 2;
    }
    @Override
    public void checkTurns(Combatant character){
        if(remainingTurns == 0){
            removeEffect(character);
        }
        else{
            decrementTurns();
            character.decCooldown();
        };
    }

    /*Characters passed in the list must still be alive */
    public void applyEffect(Combatant user, ArrayList <Combatant> characters){
        for (Combatant character: characters){
            if (character == user) {continue;};
            character.takeDamage(user.getAttack());
            if (character.isAlive() == false){
                user.setAttack(user.getAttack()+10);
            };
        };
        user.addNewEffect(this);
    }

    @Override
    public void removeEffect(Combatant character){
        super.removeEffect(character);
    }
    
}
