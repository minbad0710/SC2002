package Entity.Item;

import Entity.Combatant.Combatant;
import java.util.ArrayList;

public abstract class Item {
    protected String name;
    protected String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    abstract public Item recreate();
    public void setName(String name) {
        this.name = name;
    }

    public abstract void use(Combatant actor, ArrayList<Combatant> targets);
}
