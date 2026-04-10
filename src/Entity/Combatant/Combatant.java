package Entity.Combatant;

import Entity.SpecialSkill.SpecialSkill;
import Entity.StatusEffect.*;
import java.util.ArrayList;
import Entity.Action.*;
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
    protected ArrayList<Action> availableActions = new ArrayList<>();

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

    // Status Effect
    public void addNewEffect(StatusEffect effect){
        this.statusEffects.add(effect);
    }

    public void removeStatusEffect(StatusEffect effect){
        this.statusEffects.remove(effect);
        effect = null;
    }

    public ArrayList <StatusEffect> getEffectList(){
        return statusEffects;
    }

    // Each combatant has their special skill
    public SpecialSkill getSpecialSkill(){
        return specialSkill;
    }

    // Check the combatatnt is alive or not
    public boolean isAlive(){
        return this.getHp() >0;
    }

    public String notAlive(){
        return this.name + " is ELIMINATED";
    }
    
    // Active or not
    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isActive(){
        return this.active;
    }
    public String notActive(){
        return this.name + " is STUNNED! Turn skipped.";
    }
    
    // Cool down for special skill
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

    public ArrayList<Action> getAvailableActions() {
        return this.availableActions;
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

    public String getStatsSummary() {
        return String.format("(HP: %d, ATK: %d, DEF: %d, SPD: %d)", this.MaxHP, this.attack, this.defend, this.speed);
    }
    
    
}
