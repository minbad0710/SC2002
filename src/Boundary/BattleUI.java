package Boundary;
import Entity.Combatant.Combatant;
import Entity.Combatant.Player.Player;
import Entity.Item.Item;
import Entity.Action.Action;
import Entity.Combatant.Enemy.Enemy;
import java.util.ArrayList;

import Control.LevelManagment.LevelManagement;
public interface BattleUI {
    void displayLoadingScreen();
    void displayStartofEachRound(int round);
    void displayBattleStatus(ArrayList<Player> players, ArrayList<Enemy> enemies);
    void displayTurnResult(String message);
    void notifyBackupSpawn();
    void displayVictoryScreen(int hp, int rounds);
    void displayDefeatScreen(int enemiesLeft, int rounds);
    void displayRestartMessage(); 
    void displayReturnHomeMessage(); 
    void displayExitMessage();
    
    Player promptCharacterSelection(ArrayList<Combatant> templates);
    LevelManagement promptDifficultySelection(ArrayList<Combatant> templates, ArrayList<LevelManagement> levels);
    ArrayList<Item> promptInitialItemSelection(ArrayList<Item> availableItems);
    Action promptPlayerActionSelection(Player player, ArrayList<Enemy> activeEnemies);
    int promptGameOverOptions();
    Action promptEnemyActionSelection(Enemy enemy, Player player);
}
