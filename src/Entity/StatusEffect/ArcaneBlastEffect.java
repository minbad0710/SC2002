package Entity.StatusEffect;

import Entity.Combatant.*;
import java.util.ArrayList;

public class ArcaneBlastEffect extends StatusEffect{
    
    /*Characters passed in the list must still be alive */
    @Override
    public void applyEffect(Combatant user, ArrayList <Combatant> characters){
        for (Combatant character: characters){
            character.takeDamage(user.getAttack());
            if (character.isAlive() == false){
                user.setAttack(user.getAttack()+10);
            };
        };
    }

    @Override
    public void removeEffect(Combatant character){
        super.removeEffect(character);
    }

}
