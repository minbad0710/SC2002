package Entity.Combatant.Enemy;

import Entity.Action.BasicAttack;
import Entity.Combatant.Combatant;

public abstract class Enemy extends Combatant{
    public Enemy(int max_hp, int speed, int attack, int defend){
        super(max_hp, speed, attack, defend);
        this.specialSkill = null;
        this.availableActions.add(new BasicAttack());
    }

}
