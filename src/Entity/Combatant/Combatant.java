package Entity.Combatant;

import Entity.SpecialSkill.SpecialSkill;
import Entity.StatusEffect.*;
import java.util.ArrayList;

public abstract class Combatant {
    /*Variable */
    protected int hp;
    protected int MaxHP;
    protected int speed;
    protected int attack;
    protected int defend;
    protected String name;
    protected boolean active; /*if stunned or not */
    protected int cooldown; /*True mean unavailable */
    protected ArrayList <StatusEffect> statusEffects;
    protected SpecialSkill specialSkill;

    /*Getter & setter */
    public int getMaxHP(){
        return this.MaxHP;
    }
    public String getName(){
        return this.name;
    }
    public int getHp(){
        return this.hp;
    }
    public int getSpeed(){
        return this.speed;
    }
    public int getAttack(){
        return this.attack;
    }
    public int getDefend(){
        return this.defend;
    } 

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setDefend(int defend){
        this.defend = defend;
    }
    
    
    public void takeDamage(int damage){
        this.hp = Math.min(hp, this.hp - damage + defend);
        if (this.hp <0){
            this.hp = 0;
        };
    }

    public abstract void heal(int hp);

    
    public void addNewEffect(StatusEffect effect){
        this.statusEffects.add(effect);
    }

    public ArrayList <StatusEffect> getEffectList(){
        return statusEffects;
    }

    public SpecialSkill getSpecialSkill(){
        return specialSkill;
    }

    public void removeStatusEffect(StatusEffect effect){
        this.statusEffects.remove(effect);
        effect = null;
    }

    public boolean isAlive(){
        return this.getHp() >0;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isActive(){
        return this.active;
    }

    public void setCooldown(int n){
        this.cooldown = n;
    }

    public int getCooldown(){
        return this.cooldown;
    }

    public void reduceCooldown(){
        if (this.cooldown > 0)
        {this.cooldown--;};
    }

    abstract public void resetDefend();
    abstract public void resetAttack();

    /*Constructor */
    public Combatant(int max_hp, int speed, int attack, int defend){
        this.MaxHP = this.hp = max_hp;
        this.speed = speed;
        this.attack = attack;
        this.defend = defend;
        this.statusEffects = new ArrayList<StatusEffect>();
        this.active = true;
        this.cooldown = 0;
    }
    
    
}
