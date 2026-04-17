package Entity.SpecialSkill;

import java.util.ArrayList;
import Entity.Combatant.*;
import Entity.StatusEffect.*;
import Entity.Action.TargetType;

public class ArcaneBlast extends SpecialSkill{
    final int DURATION = 3;
    String record ="";
    // Constructor
    public ArcaneBlast (){
        super("Arcane Blast", "Each enemy defeated by Arcane Blast adds 10 to the Wizard's Attack");
    }     

    @Override
    public void execute(Combatant actor, ArrayList <Combatant> targets){
        ArcaneBlastEffect e = new ArcaneBlastEffect();
        e.applyEffect(actor, targets);
        actor.setCooldown(DURATION);     
    } 

    @Override
    public String getTargetString(Combatant actor, ArrayList <Combatant> combatants, ArrayList<Integer> previousHp) {
        String message = "All Enemies: ";
        int num = 0;
        for (int i = 0; i < combatants.size(); i++) {
            if (combatants.get(i).getHp() == 0 ){
                num++;
            }
            message += combatants.get(i).getName() + " HP: " + previousHp.get(i) + " -> " + combatants.get(i).getHp() + "; ";
        }
        message += "| Attack: " + actor.getAttack() + "| Enemies Defeated: " + num;
        return message;
    }
    
    @Override
    public TargetType getTargetType() {
        return TargetType.ALL_ENEMIES;
    }
}


