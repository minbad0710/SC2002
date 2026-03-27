package Entity.Action;

import Entity.Combatant.*;
import java.util.ArrayList;

public abstract class Action {
    protected String name;
    protected String description;

    abstract public void execute(Combatant actor, ArrayList <Combatant> targets);
    abstract public boolean isAvailable(Combatant actor);
    public String getName(){
        return this.name;
    }
    public String getdescription(){
        return this.description;
    }
}
