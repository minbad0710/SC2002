package Entity.Combatant.Player;
import Entity.SpecialSkill.*;

public class Warrior extends Player{
    private static final int MAX_HP = 260;
    private static final int SPEED = 30;
    private static final int ATTACK = 40;
    private static final int DEFEND = 20;

    public Warrior(){
        super(MAX_HP, SPEED, ATTACK, DEFEND);
        this.specialSkill = new ShieldBash();
        this.name = "Warrior";
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