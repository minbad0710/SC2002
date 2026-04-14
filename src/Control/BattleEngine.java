package Control;

import Entity.Combatant.Combatant;
import Entity.Combatant.Enemy.Enemy;
import Entity.Combatant.Player.Player;
import Entity.StatusEffect.StatusEffect;
import Entity.Action.Action;
import Control.LevelManagment.LevelManagement;
import Boundary.BattleOutPutUI;
import Boundary.BattleInPutUI;
import java.util.ArrayList;
import Control.TurnOrderStrategy.TurnOrderStrategy;
import Control.Environment.Environment;

public class BattleEngine {
    private Player player;
    private ArrayList<Enemy> activeEnemies;
    private LevelManagement level;
    private TurnOrderStrategy turnStrategy;
    private int currentRound = 0;
    private BattleInPutUI input;
    private BattleOutPutUI output;
    private Environment environment;

    public BattleEngine(Player player, LevelManagement level, TurnOrderStrategy strategy, BattleInPutUI input, BattleOutPutUI output, Environment environment) {
        this.player = player; // this will connect with the promptCharacterSelection() in GameCLI when we connect them in the main
        this.level = level; // this will connect with the promptDifficultySelection() in GameCLI when we connect them in the main
        this.turnStrategy = strategy; // this will be the speed strategy
        this.input = input;
        this.output = output;
        this.currentRound = 0; // counter for the number of rounds to display at the end
        this.activeEnemies = level.getInitialSpawns(); // initially, the enemy list will be filled with the initial spawns.
        this.environment = environment; // this will connect with the promptEnvironmentSelection() in GameCLI when we connect them in the main
    }

    public int startBattle(){
        output.displayLoadingScreen(); // Display loading screen when player starts the game
        // Start the loop of the battle. The loop will end if the game is over 
        while(!this.isGameOver()){
            this.currentRound ++;// increment each round in order to count the number of ground
            this.output.displayStartofEachRound(currentRound); // display the start of each round
            this.playRound(); // this will process each round of the battle
            
            this.checkAndSpawnBackups(); // after each round, check the backup spawn condition
        }
        // When the game is over 
        if(player.isAlive()){
            output.displayVictoryScreen(player, currentRound); // this for victory
        } 
        else{
            output.displayDefeatScreen(activeEnemies.size(), currentRound); // this for defeat
        }
        // user's choice after game finished
        return input.promptGameOverOptions();
    }

    // each turn we check the applying status effect of each combatant
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

    // process of each round
    private void playRound() {
        // each round, we will add all the combatants to the list
        ArrayList<Combatant> allCombatants = new ArrayList<>();
        allCombatants.add(player); // add player to the list
        allCombatants.addAll(activeEnemies); // add all alive ememies 
        checkStatusEffects(allCombatants); // check the status effect for all combatants at the start of each round
        // sort the combatants list according to TurnOrderStrategy 
        ArrayList<Combatant> turnOrder = turnStrategy.determineTurnOrder(allCombatants);
        output.displayTurnOrder(turnOrder);
        // process turn for each combatant
        int size = turnOrder.size();
        for (int i = 0; i < size ; i++) {
            Combatant c = turnOrder.get(i);
            if(c.isAlive() && !this.isGameOver()){
                processTurn(c); // combatant executes action
                if(!player.isAlive()){
                    output.displayTurnResult(player.notAlive());
                    break; // game is over
                }
                for(int j = 0; j < activeEnemies.size(); j++) {
                    Enemy e = activeEnemies.get(j);
                    if (!e.isAlive()){
                        output.displayTurnResult(e.notAlive());
                        activeEnemies.remove(e); 
                        j--;
                    }
                }
            }
            else if (this.isGameOver()){
                break; // if the game is over, break the loop to end the battle
            }
        };

        if (!this.isGameOver()){
            environment.applyEnvironmentEffect(player); // apply environment effect to player
            int i =0;
            output.displayTurnResult(environment.getResultMessage()); // display the environment effect message after applying the effect
            while (!this.isGameOver() && i < activeEnemies.size()) {
                Enemy e = activeEnemies.get(i);
                environment.applyEnvironmentEffect(e); // apply environment effect to each enemy
                output.displayTurnResult(environment.getResultMessage()); // display the environment effect message after applying the effect
                if (!e.isAlive()){
                    output.displayTurnResult(e.notAlive());
                    activeEnemies.remove(e);
                    continue;
                }
                i++;
            };
        }
        output.displayBattleStatus(new ArrayList<Player>(){{add(player);}}, activeEnemies, currentRound);
    }

    // process turn for each combatant
    private void processTurn(Combatant c) {
        if (!c.isActive()) {
            output.displayTurnResult(c.notActive()); // when combatant is stunned
            return;
        }
        // reduce cooldown for player
        if (c instanceof Player) {
            Player p = (Player) c;
            p.reduceCooldown();
        } 
        // combatant choose action
        Action action = input.promptActionSelection(c, player, activeEnemies);
        // after choosing action, exceute it
        action.execute(c);
        output.displayTurnResult(action.getResultMessage()); // display the result of the action after executing it
        action.clearTargets(); // clear the target list after executing the action to avoid confusion in the next turn
    }

    // method for backup spawn 
    private void checkAndSpawnBackups() {
        if (activeEnemies.isEmpty() && level.hasBackupSpawns()) {
            activeEnemies.addAll(level.getBackupSpawns());
            level.changeBackupSpawnStatus(false); // change the backup spawn status to false to avoid multiple spawn
            this.output.notifyBackupSpawn(); // notify the player that backup spawn is triggered
        }
    }

    // check if the game is over or not
    private boolean isGameOver() {
        return !player.isAlive() || (activeEnemies.isEmpty() && !level.hasBackupSpawns());
        // player is defeated or all enemies are defeated
    }
}