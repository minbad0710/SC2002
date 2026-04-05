package Entity.Action;


import Entity.Combatant.Combatant;

import Entity.SpecialSkill.SpecialSkill;

public class UseSpecialSkill extends Action{
    public UseSpecialSkill(){
        this.name = "Special Skill";
        this.description = "Using special skill";
    }
    @Override
    public void execute(Combatant actor){
        SpecialSkill s = actor.getSpecialSkill();
        s.execute(actor, targets);
        this.resultMessage = actor.getName() + " uses " + s.getName() + ". " + s.getDescription();
    }
    
    @Override
    public boolean isAvailable(Combatant actor){
        return actor.getCooldown()==0;
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        SpecialSkill s = actor.getSpecialSkill();
        return s.getTargetType();
    }

}
