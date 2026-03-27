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

    public abstract void use(Combatant actor, ArrayList<Combatant> targets);
}
