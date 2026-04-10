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
        int previousHp = target.getHp();
        target.takeDamage(actor.getAttack());
        if (actor.getAttack() == 0) {
            this.resultMessage = actor.getName() + " -> " + this.name + " -> " + target.getName() + ": HP: " + previousHp + " -> " + target.getHp() + " (Damage: 0) (Smoke Bomb active)";
        } else {
        this.resultMessage = actor.getName() + " -> " + this.name + " -> " + target.getName() + ": HP: " + previousHp + " -> " + target.getHp() + " (Damage: " + actor.getAttack() + " - " + target.getDefend() + " = " + Math.max(0, actor.getAttack() - target.getDefend()) + ")";
        }
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
