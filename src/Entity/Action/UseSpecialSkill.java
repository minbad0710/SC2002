package Entity.Action;

import Entity.Combatant.Combatant;
import Entity.SpecialSkill.SpecialSkill;
import java.util.ArrayList;

public class UseSpecialSkill extends Action{
    // Constructor
    public UseSpecialSkill(){
        this.name = "Special Skill";
        this.description = "Using special skill";
    }
    @Override
    public void execute(Combatant actor){
        SpecialSkill s = actor.getSpecialSkill();
        ArrayList<Integer> previousHp = new ArrayList<>();
        for (Combatant target : targets) {
            previousHp.add(target.getHp());
        }
        s.execute(actor, targets);
        this.resultMessage = actor.getName() + " -> " + s.getName() + " -> " + s.getTargetString(actor, targets, previousHp) + "| Cooldown: " + actor.getCooldown(); // the message after using this special skill, which is the same as the message after using the special skill, but with a note about cooldown
    }
    
    @Override
    public boolean isAvailable(Combatant actor){
        return actor.getCooldown() == 0;
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        SpecialSkill s = actor.getSpecialSkill();
        return s.getTargetType();
    }

}
