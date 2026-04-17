package Boundary;
import Entity.Combatant.Combatant;
import Entity.Combatant.Player.Player;
import Entity.Item.Item;
import Entity.Action.Action;
import Entity.Combatant.Enemy.Enemy;
import java.util.ArrayList;
import Control.LevelManagment.LevelManagement;
import Control.Environment.Environment;

public interface BattleInPutUI {
    Player promptCharacterSelection(ArrayList<Combatant> templates);
    LevelManagement promptDifficultySelection(ArrayList<Combatant> templates, ArrayList<LevelManagement> levels);
    ArrayList<Item> promptInitialItemSelection(ArrayList<Item> availableItems);
    Action promptActionSelection(Combatant c, Player p, ArrayList<Enemy> e);
    Environment promptEnvironmentSelection(ArrayList<Environment> environments);
    int promptGameOverOptions();
}
