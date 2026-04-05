package Entity.SpecialSkill;

import java.util.ArrayList;

import Entity.Combatant.*;
import Entity.StatusEffect.*;
import Entity.Action.TargetType;
public class ArcaneBlast extends SpecialSkill{
    final int DURATION = 3;
    public ArcaneBlast (){
        super("Arcane Blast", "Each enemy defeated by Arcane Blast adds 10 to the Wizard’s Attack");
    }       
    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets ){
        ArcaneBlastEffect e = new ArcaneBlastEffect();
        e.applyEffect(actor, targets);
        actor.setCooldown(DURATION);     
    } 
    
    @Override
    public TargetType getTargetType() {
        return TargetType.ALL_ENEMIES;
    }
}


