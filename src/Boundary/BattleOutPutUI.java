package Boundary;
import Entity.Combatant.Player.Player;
import Entity.Combatant.Enemy.Enemy;
import java.util.ArrayList;
import Entity.Combatant.*;
public interface BattleOutPutUI {
    void displayLoadingScreen();
    void displayStartofEachRound(int round);
    void displayBattleStatus(ArrayList<Player> players, ArrayList<Enemy> enemies, int curround);
    void displayTurnOrder(ArrayList<Combatant> turnOrder);
    void displayTurnResult(String message);
    void notifyBackupSpawn();
    void displayVictoryScreen(Player p, int rounds);
    void displayDefeatScreen(int enemiesLeft, int rounds);
    void displayGameOverMessage(int choice);

}
