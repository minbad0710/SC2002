package Entity.Action;

import Entity.Combatant.*;
import java.util.ArrayList;

public abstract class Action {
    protected String name;
    protected String description;
    protected ArrayList<Combatant> targets = new ArrayList<>(); // each action has a specific target set
    protected String resultMessage; // display the action

    public abstract void execute(Combatant actor);

    abstract public boolean isAvailable(Combatant actor);

    public String getName(){
        return this.name;
    }

    public ArrayList<Combatant> getTargets() {
        return this.targets;
    }
    
    public String getdescription(){
        return this.description;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void addTarget(Combatant target) {
        this.targets.add(target);
    }

    public void setTargets(ArrayList<Combatant> targets) {
        this.targets = targets;
    }

    public void clearTargets(){
        this.targets.clear();
    }

    public abstract TargetType getTargetType(Combatant actor); // each action has its target type

}
