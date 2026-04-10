package Entity.Combatant.Enemy;

public class Wolf extends Enemy {
    private static final int MAX_HP = 40;
    private static final int SPEED = 35;
    private static final int ATTACK = 45;
    private static final int DEFEND = 5;

    public Wolf(String name){
        super(MAX_HP, SPEED, ATTACK, DEFEND);
        this.name = name;
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
}