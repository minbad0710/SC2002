package Entity.Combatant.Player;

import Entity.SpecialSkill.*;

public class Wizard extends Player {
    protected final static int MAX_HP = 200;
    protected final static int SPEED = 20;
    protected final static int ATTACK = 50;
    protected final static int DEFEND = 10;
    public Wizard() {
        super(MAX_HP, SPEED, ATTACK, DEFEND);
        this.specialSkill = new ArcaneBlast();
        this.name ="Wizard";
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
    public void resetAttack(){
        this.setAttack(ATTACK);
    }

    @Override
    public void resetDefend(){
        this.setDefend(DEFEND);
    }

    @Override
    public Player clonePlayer() {
        return new Wizard();
    }
    
}