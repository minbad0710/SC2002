package Entity.SpecialSkill;

import Entity.Combatant.*;
import java.util.ArrayList;

public abstract class SpecialSkill {
    protected String name;
    protected String description;

    public abstract void execute(Combatant actor, ArrayList <Combatant> combatants);

    public String getName(){
        return this.name;
    }

    public String description(){
        return this.description;
    }

    public SpecialSkill(String name, String descrition){
        this.name = name;
        this.description = "...";
    }
}
