package Entity.Action;

import Entity.Combatant.*;
import Entity.StatusEffect.DefendEffect;

import java.util.ArrayList;

public class Defend extends Action{
    public Defend(){
        this.name = "Defend";
        this.description ="Increase defend by 10 in the current and next round";
    }

    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets){
        DefendEffect d = new DefendEffect();
        ArrayList<Combatant> user = new ArrayList<Combatant>();
        user.add(actor);
        d.applyEffect(user);
    }

    @Override
    public boolean isAvailable(Combatant actor){
        return true; // check stunned in battle engine
    }

    
}