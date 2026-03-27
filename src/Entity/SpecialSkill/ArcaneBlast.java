package Entity.SpecialSkill;

import java.util.ArrayList;

import Entity.Combatant.*;
import Entity.StatusEffect.*;

public class ArcaneBlast extends SpecialSkill{
    public ArcaneBlast (){
        super("Arcane Blast", "...");
    }       
    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets ){
        ArcaneBlastEffect e = new ArcaneBlastEffect();
        e.applyEffect(actor, targets);
        actor.setCooldown(2);
        
    } 
    
}


