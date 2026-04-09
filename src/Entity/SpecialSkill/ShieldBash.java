package Entity.SpecialSkill;

import Entity.Combatant.Combatant;
import Entity.StatusEffect.*;
import java.util.ArrayList;
import Entity.Action.BasicAttack;
import Entity.Action.TargetType;

public class ShieldBash extends SpecialSkill{
    final int DURATION = 3;
    // Constructor
    public ShieldBash(){
        super("ShieldBash", "Affected entity is unable to take actions for the current turn and the next turn.");
    }
    
    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets){
        BasicAttack attack = new BasicAttack();
        attack.setTargets(targets);
        attack.execute(actor);
        Stun s = new Stun();
        s.applyEffect(actor,targets);
        actor.setCooldown(DURATION);
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SINGLE_ENEMY;
    }
}   
