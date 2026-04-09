package Entity.Action;

import Entity.Combatant.Combatant;

public class BasicAttack extends Action {
    // Constructor
    public BasicAttack(){
        this.name = "Basic Attack";
        this.description ="Attack targeted enemy";
    }

    @Override
    public void execute(Combatant actor){
        Combatant target = targets.get(0); // target on single enemy
        target.takeDamage(actor.getAttack());
        this.resultMessage = actor.getName() + " attacks " + targets.get(0).getName();
    }

    @Override
    public boolean isAvailable(Combatant actor){
        return true; // combatants can always do basicattack unless they are not active
    }

    @Override
    public TargetType getTargetType(Combatant actor) {
        return TargetType.SINGLE_ENEMY;
    }

    
}
