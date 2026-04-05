package Control;

import Entity.Combatant.Combatant;
import Entity.Combatant.Enemy.Enemy;
import Entity.Combatant.Player.Player;
import Entity.StatusEffect.StatusEffect;
import Entity.Action.*;
import Control.LevelManagment.LevelManagement;
import Boundary.BattleUI;
import java.util.ArrayList;
import Control.TurnOrderStrategy.TurnOrderStrategy;

public class BattleEngine {
    private Player player;
    private ArrayList<Enemy> activeEnemies;
    private LevelManagement level;
    private TurnOrderStrategy turnStrategy;
    private int currentRound = 0;
    private BattleUI ui;

    public BattleEngine(Player player, LevelManagement level, TurnOrderStrategy strategy, BattleUI ui) {
        this.player = player; // this will connect with the promptCharacterSelection() in GameCLI when we connect them in the main
        this.level = level; // this will connect with the promptDifficultySelection() in GameCLI when we connect them in the main
        this.turnStrategy = strategy; // this will be the speed strategy
        this.ui = ui;
        this.currentRound = 0; // counter for the number of rounds to display at the end
        this.activeEnemies = level.getInitialSpawns(); // initially, the enemy list will be filled with the initial spawns.
    }

    public int startBattle(){
        // Display loading screen when player starts the game
        ui.displayLoadingScreen();
        // Start the loop of the battle. The loop will end if the game is over 
        while(!this.isGameOver()){
            this.currentRound ++;// increment each round in order to count the number of ground
            this.ui.displayStartofEachRound(currentRound); // display the start of each round

            this.playRound(); // this will process each round of the battle
            this.checkAndSpawnBackups(); // after each round, check the backup spawn condition
        }
        // When the game is over 
        if(player.isAlive()){
            ui.displayVictoryScreen(player.getHp(), currentRound); // this for victory
        } 
        else{
            ui.displayDefeatScreen(activeEnemies.size(), currentRound); // this for defeat
        }

        return ui.promptGameOverOptions();
    }

    private void checkStatusEffects(ArrayList<Combatant> combatants) {
        for (Combatant c : combatants) {
            int size = c.getEffectList().size();
            for (int i = 0; i < c.getEffectList().size(); i++) {
                StatusEffect effect = c.getEffectList().get(i);
                effect.checkTurns(c);
                if (size != c.getEffectList().size()){
                    i--;
                };
                size = c.getEffectList().size();
            };
        }
    }

    private void playRound() {
        // each round, we will add all the combatants to the list
        ArrayList<Combatant> allCombatants = new ArrayList<>();
        allCombatants.add(player); // add player to the list
        allCombatants.addAll(activeEnemies); // add all alive ememies 
        checkStatusEffects(allCombatants); // check the status effect for all combatants at the start of each round
        // sort the combatants list according to TurnOrderStrategy 
        ArrayList<Combatant> turnOrder = turnStrategy.determineTurnOrder(allCombatants);

        int size = turnOrder.size();
        for (int i = 0; i < size ; i++) {
            Combatant c = turnOrder.get(i);
            if(c.isAlive() && !this.isGameOver()){
                processTurn(c);
            }
            else if (this.isGameOver()){
                break; // if the game is over, break the loop to end the battle
            }
            
        };
        
        for (int i = 0; i < activeEnemies.size(); i++) {
            Enemy e = activeEnemies.get(i);
            if (!e.isAlive()){
                activeEnemies.remove(e); // if the combatant is defeated during the round, remove it from the active enemies list to avoid processing its turn in the next round
                i--;
            };
        };

        ui.displayBattleStatus(new ArrayList<Combatant>(){{add(player);}}, activeEnemies);

    }

    private void processTurn(Combatant c) {
        if (!c.isActive()) {
            ui.displayTurnResult(c.notActive());
            return;
        }
        Action action;

        if (c instanceof Player) {
            Player p = (Player) c;
            p.reduceCooldown();
            action = ui.promptPlayerActionSelection(p, activeEnemies);
        } 
        else {
            action = ui.promptEnemyActionSelection(player);
        }

        action.execute(c);
        ui.displayTurnResult(action.getResultMessage()); 
    }
    // method for backup spawn 
    private void checkAndSpawnBackups() {
        if (activeEnemies.isEmpty() && level.hasBackupSpawns()) {
            activeEnemies.addAll(level.getBackupSpawns());
            level.changeBackupSpawnStatus(false); // change the backup spawn status to false to avoid multiple spawn
            this.ui.notifyBackupSpawn(); // notify the player that backup spawn is triggered
        }
    }
    // check if the game is over or not
    private boolean isGameOver() {
        return !player.isAlive() || (activeEnemies.isEmpty() && !level.hasBackupSpawns());
        // player is defeated or all enemies are defeated
    }
}