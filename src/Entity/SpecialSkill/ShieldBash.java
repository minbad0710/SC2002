package Entity.SpecialSkill;

import Entity.Combatant.Combatant;
import Entity.StatusEffect.*;
import java.util.ArrayList;
import Entity.Action.BasicAttack;
public class ShieldBash extends SpecialSkill{
    /**
     * 
     */
    final int DURATION = 3;
    public ShieldBash(){
        super("ShieldBash", "Affected entity is unable to take actions for the current turn and the next turn.");
    }

    public void execute(Combatant actor, ArrayList <Combatant> targets)
    {
        BasicAttack attack = new BasicAttack();
        attack.execute(actor, targets);
        Stun s = new Stun();
        actor.setCooldown(DURATION);
        s.applyEffect(actor,targets);
    }
}   
