package Entity.StatusEffect;
import Entity.Combatant.*;
import java.util.ArrayList;

public class DefendEffect extends StatusEffect{

    final private int BOOST = 10;

    @Override
    public void applyEffect(ArrayList <Combatant> characters) {
        for (Combatant character: characters){
        character.setDefend(character.getDefend() + BOOST);
        };
        super.applyEffect(characters);
    }


    @Override
    public void removeEffect(Combatant character){
        character.resetDefend();
        super.removeEffect(character);
    }

}