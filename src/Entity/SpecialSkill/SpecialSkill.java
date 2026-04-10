package Entity.SpecialSkill;

import Entity.Combatant.*;
import java.util.ArrayList;
import Entity.Action.TargetType;
public abstract class SpecialSkill {
    protected String name;
    protected String description;

    public abstract void execute(Combatant actor, ArrayList <Combatant> combatants);

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public abstract String getTargetString(Combatant actor, ArrayList <Combatant> combatants, ArrayList<Integer> previousHp); // the string to display after using this special skill, with the target and the hp change of target

    public SpecialSkill(String name, String description){
        this.name = name;
        this.description = description;
    }

    public abstract TargetType getTargetType();
}
