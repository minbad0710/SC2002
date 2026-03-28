package Control.TurnOrderStrategy;
import Entity.Combatant.Combatant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class SpeedOrderStrategy implements TurnOrderStrategy{
    @Override
    public ArrayList<Combatant> determineTurnOrder(ArrayList<Combatant> combatants){
        ArrayList<Combatant> sortedList = new ArrayList<>(combatants);
        Collections.sort(sortedList, new Comparator<Combatant>(){
            @Override
            public int compare(Combatant c1, Combatant c2) {
                return Integer.compare(c2.getSpeed(), c1.getSpeed());/*sort speed in descending order */
            }
        });
        return sortedList;
    }
}
