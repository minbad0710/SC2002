package Boundary;
import Entity.Combatant.Combatant;
import Entity.Combatant.Enemy.Enemy;
import Entity.Combatant.Player.Player;
import Entity.Item.Item;
import Entity.Action.Action;

import java.util.ArrayList;

import Control.LevelManagment.LevelManagement;
public interface BattleUI {
    void displayLoadingScreen();
    void displayStartofEachRound(int round);
    void displayBattleStatus(ArrayList<Combatant> players, ArrayList<Enemy> enemies);
    void displayTurnResult(String message);
    void notifyBackupSpawn();
    void displayVictoryScreen(int hp, int rounds);
    void displayDefeatScreen(int enemiesLeft, int rounds);
    void displayRestartMessage(); 
    void displayReturnHomeMessage(); 
    void displayExitMessage();
    
    Player promptCharacterSelection(ArrayList<Player> templates);
    LevelManagement promptDifficultySelection(ArrayList<LevelManagement> levels);
    ArrayList<Item> promptInitialItemSelection(ArrayList<Item> availableItems);
    Action promptPlayerActionSelection(Player player, ArrayList<Enemy> activeEnemies);
    int promptGameOverOptions();
    Action promptEnemyActionSelection(Player player);
}
