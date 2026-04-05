package Entity.Item;

import Entity.Combatant.Combatant;
import java.util.ArrayList;

import Entity.Action.TargetType;
public abstract class Item {
    protected String name;
    protected String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void use(Combatant actor, ArrayList<Combatant> targets);

    public abstract TargetType getTargetType(Combatant actor);
}
