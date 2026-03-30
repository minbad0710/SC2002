package Control;

import Entity.Combatant.Combatant;
import Entity.Combatant.Enemy.Enemy;
import Entity.Combatant.Player.Player;
import Entity.Combatant.Player.Wizard;
import Entity.Combatant.Player.Warrior;
import Entity.Item.Item;
import Entity.Item.Potion;
import Entity.Item.PowerStone;
import Entity.Item.SmokeBomb;
import Entity.StatusEffect.StatusEffect;
import Entity.Action.*;
import Control.LevelManagment.LevelManagement;
import Boundary.GameCLI;
import java.util.ArrayList;
import Control.TurnOrderStrategy.TurnOrderStrategy;

public class BattleEngine {
    private Player player;
    private ArrayList<Enemy> activeEnemies;
    private LevelManagement level;
    private TurnOrderStrategy turnStrategy;
    private int currentRound = 0;
    private GameCLI ui;

    public BattleEngine(Player player, LevelManagement level, TurnOrderStrategy strategy, GameCLI ui) {
        this.player = player; // this will connect with the promptCharacterSelection() in GameCLI when we connect them in the main
        this.level = level; // this will connect with the promptDifficultySelection() in GameCLI when we connect them in the main
        this.turnStrategy = strategy; // this will be the speed strategy
        this.ui = ui;
        this.currentRound = 0; // counter for the number of rounds to display at the end
        this.activeEnemies = level.getInitialSpawns(); // initially, the enemy list will be filled with the initial spawns.
    }

    public void startBattle(){
        // Display loading screen when player starts the game
        ui.displayLoadingScreen();
        // start the loop of the battle. The loop will end if the game is over 
        while(!this.isGameOver()){
            this.currentRound ++;// increment each round in order to count the number of ground
            System.out.println("-----------Round " + currentRound + " starts!-----------");

            this.playRound(); // this will process each round of the battle
            this.checkAndSpawnBackups(); // after each round, check the backup spawn condition
        }
        // when the game is over 
        if(player.isAlive()){
            ui.displayVictoryScreen(player.getHp(), currentRound); // this for victory
        } 
        else{
            ui.displayDefeatScreen(activeEnemies.size(), currentRound); // this for defeat
        }
    }

    private void playRound() {
        // each round, we will add all the combatants to the list
        ArrayList<Combatant> allCombatants = new ArrayList<>();
        allCombatants.add(player); // add player to the list
        allCombatants.addAll(activeEnemies); // add all alive ememies 

        // sort the combatants list according to TurnOrderStrategy 
        ArrayList<Combatant> turnOrder = turnStrategy.determineTurnOrder(allCombatants);

        int size = turnOrder.size();
        for (int i = 0; i < turnOrder.size(); i++) {
            Combatant c = turnOrder.get(i);
            if(c.isAlive() && !this.isGameOver()){
                processTurn(c);
            }
            else if (this.isGameOver()){
                break; // if the game is over, break the loop to end the battle
            }
            else if (!c.isAlive() && c instanceof Enemy){ // if the combatant is an enemy and is defeated, remove it from the active enemies list and turn order list
                activeEnemies.remove(c);
                turnOrder.remove(c); // if the combatant is defeated during the round, remove it from the turn order list to avoid processing its turn
                i--; // adjust index after removal
            };
        };

        size = activeEnemies.size();
        for (int i = 0; i < activeEnemies.size(); i++) {
            Enemy e = activeEnemies.get(i);
            if (size != activeEnemies.size()){
                i--;
                activeEnemies.remove(e); // if the combatant is defeated during the round, remove it from the active enemies list to avoid processing its turn in the next round
            };
            size = activeEnemies.size();
        };

        ui.displayBattleStatus(new ArrayList<Combatant>(){{add(player);}}, activeEnemies);

    }

    private void processTurn(Combatant c) {

        

        int size = c.getEffectList().size();
        for (int i = 0; i < c.getEffectList().size(); i++) {
            StatusEffect effect = c.getEffectList().get(i);
            effect.checkTurns(c);
            if (size != c.getEffectList().size()){
                i--;
            };
            size = c.getEffectList().size();
        };

        if(!c.isActive()) {
            ui.displayTurnResult(c.getName() + "-> STUNNED: Turn skipped"); // if player is stunned, skip turn
            return;
        };


        // First case : Player's turn
        if(c instanceof Player){ // start turn, reduce cooldown by 1
            boolean actionExecuted = false;
            while (!actionExecuted) {
                int choice = ui.promptActionSelection(4); // player decides action
                ArrayList<Combatant> targets = new ArrayList<>(); // list of targets for the action
                Action selectedAction = null; // the action that player selected, I initialize it as null and also action for polymorphism 
                switch (choice) {
                    case 1: 
                        selectedAction = new BasicAttack(); // polymorphism
                        int target_index = ui.promptTargetSelection(activeEnemies); // the target enemy player chooses
                        targets.add(activeEnemies.get(target_index)); // add to the target list
                        selectedAction.execute(player, targets); // execute the action
                        actionExecuted = true; // if action is executed, meaing that end the loop

                        break;

                    case 2:
                        selectedAction = new Defend();// polymorphism
                        selectedAction.execute(player, targets); // execute the action
                        actionExecuted = true; // if action is executed, meaning that end the loop

                        break;

                    case 3: 
                        selectedAction = new UseItem(); // polymorphism
                        // check the execution is available or not
                        if(!selectedAction.isAvailable(player)){
                            ui.displayTurnResult("Inventory is empty. Please, choose another action!");
                            continue; // if not ask user to choose another action
                        }

                        ArrayList<Item> inventory = player.getInventory();
                        int item_index = ui.promptItemSelection(inventory); // asking user to choose item
                        Item chosenItem = inventory.get(item_index); // accessing the inventory list to find the selected item

                        ((UseItem)selectedAction).setItem(chosenItem); /*casting */
                        // check what item is selected
                        if (chosenItem instanceof Potion) {
                            selectedAction.execute(player, targets); // for potion, the target can be empty
                            ui.displayTurnResult(player.getName() + " uses Potion! +100 HP.");
                        } 
                        if (chosenItem instanceof PowerStone) {
                            selectedAction.execute(player, targets); // for PowerStone, the target can be empty
                            ui.displayTurnResult(player.getName() + " uses Power Stone! Special Skill is ready.");
                        } 
                        if (chosenItem instanceof SmokeBomb) {
                            targets.addAll(activeEnemies); // for smoke bomb, the target is all alive enemies
                            selectedAction.execute(player, targets);
                            ui.displayTurnResult(player.getName() + " uses Smoke Bomb! Enemy attacks will deal 0 damage.");
                        }
                        actionExecuted = true; // if action is executed, meaning that end the loop

                        break;

                    case 4:
                        selectedAction = new UseSpecialSkill(); // polymorphism
                        // check the execution is available or not
                        if(!selectedAction.isAvailable(player)){
                            ui.displayTurnResult("Skill is on cooldown. Please, choose another action!");
                            continue; // if not ask user to choose another action
                        }
                        // check what player is to choose the true special skill and target
                        if(player instanceof Wizard){
                            targets.addAll(activeEnemies); // Arcane Blast will target all alive enemies
                            selectedAction.execute(player, targets);
                            ui.displayTurnResult(player.getName() + " cast Arcane Blast on all enemies!");
                        } 
                        if(player instanceof Warrior){
                            int enemy_index = ui.promptTargetSelection(activeEnemies); // the target is chosen by player
                            targets.add(activeEnemies.get(enemy_index));
                            selectedAction.execute(player, targets);
                            ui.displayTurnResult(player.getName() + " used Shield Bash on " + activeEnemies.get(enemy_index).getName());
                        };

                        
                        actionExecuted = true; // if action is executed, meaning that end the loop
 
                        break;
                }
            }
        } 
        // Second case : Enemy's turn
        else{
            Enemy enemy = (Enemy) c; // casting
            // Basic attack execute 
            BasicAttack attack = new BasicAttack();
            ArrayList<Combatant> targets = new ArrayList<>();
            targets.add(player);
            attack.execute(enemy, targets);
            // Printing
            ui.displayTurnResult(enemy.getName() + " attacks " + player.getName());
        }
    }
    // method for backup spawn 
    private void checkAndSpawnBackups() {
        if (activeEnemies.isEmpty() && level.hasBackupSpawns()) {
            activeEnemies.addAll(level.getBackupSpawns());
            ui.displayTurnResult("All initial enemies eliminated → Backup Spawn triggered!");
        }
    }
    // check if the game is over or not
    private boolean isGameOver() {
        return !player.isAlive() || (activeEnemies.isEmpty() && !level.hasBackupSpawns());
        // player is defeated or all enemies are defeated
    }
}