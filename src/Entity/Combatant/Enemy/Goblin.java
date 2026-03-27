package Entity.Combatant.Enemy;

import Entity.Action.*;

public class Goblin extends Enemy {
    private static final int MAX_HP = 55;
    private static final int SPEED = 25;
    private static final int ATTACK = 35;
    private static final int DEFEND = 15;

    public Goblin(){
        super(MAX_HP, SPEED, ATTACK, DEFEND);
        this.name = "Goblin";
    }

    @Override
    public void heal(int hp){
        if (this.hp + hp > MAX_HP){
            this.hp = MAX_HP;
        }
        else {
            this.hp += hp;
        }
    }

    @Override
    public void resetAttack() {
        this.setAttack(ATTACK);
    }
    @Override
    public void resetDefend() {
        this.setDefend(DEFEND);
    }

    @Override
    public Action decideAction(){
        Action action = new BasicAttack();
        return action;
    }
}
