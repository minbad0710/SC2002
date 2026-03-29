package Control.TurnOrderStrategy;
import Entity.Combatant.Combatant;

import java.util.ArrayList;
public interface TurnOrderStrategy {
    public ArrayList<Combatant> determineTurnOrder(ArrayList<Combatant> combatants);
}
