package Entity.Action;



import Entity.Combatant.Combatant;

public class BasicAttack extends Action {
    public BasicAttack(){
        this.name = "Basic Attack";
        this.description ="Attack targeted enemy";
    }

    @Override
    public void execute(Combatant actor){
        for (Combatant target: targets){
            if (target == actor){continue;};
            target.takeDamage(actor.getAttack());
        };
        this.resultMessage = actor.getName() + " attacks " + targets.get(0).getName() + " for " + actor.getAttack() + " damage.";
    }

    @Override
    public boolean isAvailable(Combatant actor){
        return true; // check stunned in battle engine
    }

    public TargetType getTargetType(Combatant actor) {
        return TargetType.SINGLE_ENEMY;
    }

}
