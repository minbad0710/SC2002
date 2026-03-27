package Entity.SpecialSkill;

import Entity.Combatant.Combatant;
import Entity.StatusEffect.*;
import java.util.ArrayList;

public class ShieldBash extends SpecialSkill{
    /**
     * 
     */
    final int DURATION = 2;
    public ShieldBash(){
        super("Warrior", "...");
    }

    public void execute(Combatant actor, ArrayList <Combatant> targets)
    {
        Stun s = new Stun();
        actor.setCooldown(2);
        s.applyEffect(actor,targets);
    }
}   
