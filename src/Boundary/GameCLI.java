package Boundary;

import Entity.Action.UseItem;
import Entity.Combatant.Combatant;
import Entity.Combatant.Enemy.Enemy;
import Entity.Combatant.Player.Player;
import Entity.Item.Item;
import Entity.Action.Action;
import java.util.ArrayList;
import java.util.Scanner;
import Control.LevelManagment.LevelManagement;
import Entity.Action.TargetType;
import Control.Environment.Environment;

public class GameCLI implements BattleInPutUI, BattleOutPutUI{
    private Scanner scanner;

    public GameCLI() {
        this.scanner = new Scanner(System.in);
    }
    /*display method*/
    public void displayLoadingScreen() {
        System.out.println("========================================");
        System.out.println("   WELCOME TO TURN-BASED COMBAT ARENA   ");
        System.out.println("========================================\n");
    }
    public void displayStartofEachRound(int round){
        System.out.println("-----------Round " + round + " starts!-----------");
    }
    public void displayBattleStatus(ArrayList <Player> players, ArrayList<Enemy> enemies, int currentRound) {
        System.out.println("\nEnd of Round " + currentRound + ":");
        for (Player p : players) {
            System.out.printf("[Player] %s | HP: %d/%d | ATK: %d | DEF: %d | SPD: %d | ",
                p.getName(), p.getHp(), p.getMaxHP(), p.getAttack(), p.getDefend(), p.getSpeed());
            ArrayList<Item> currentlist = new ArrayList<>(p.getInventory());
            ArrayList<Item> initiallist = p.getinitiallist();

            for (int i = 0; i < initiallist.size(); i++) {
                Item initial_item = initiallist.get(i);
                int status = 0;

                for (int j = 0; j < currentlist.size(); j++) {
                    if (currentlist.get(j).getName().equals(initial_item.getName())) {
                        status += 1;
                    }
                }
                System.out.print(initial_item.getName() + ": " + status);
                System.out.print(" | ");
            }
            System.out.printf("Special Skills Cooldown: %d\n", p.getCooldown());
        }
        System.out.println("---------------------");
        for (Enemy e : enemies) {
            System.out.printf("[Enemy] %s | HP: %d/%d | ATK: %d | DEF: %d | SPD: %d\n",
                e.getName(), e.getHp(), e.getMaxHP(), e.getAttack(), e.getDefend(), e.getSpeed());
        }
        System.out.println("---------------------\n");
    }

    public void displayTurnOrder(ArrayList<Combatant> turnOrder){
        System.out.print("\nTurn Order: ");
        for (int i = 0; i < turnOrder.size(); i++) {
            Combatant c = turnOrder.get(i);
            System.out.print(c.getName() + " (SPD " + c.getSpeed() + ")");
            if (i < turnOrder.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\n");
    }

    public void displayTurnResult(String message) {
        if  (message ==""){
            return;
        }
        System.out.println(">> " + message);
    }

    public void notifyBackupSpawn(){
        System.out.println("All initial enemies eliminated -> Backup Spawn triggered!");
    }

    public void displayVictoryScreen(Player p, int rounds) {
        System.out.println("\n***************************************************************");
        System.out.println("      Congratulations, you have deffeated all your enemies.      ");
        System.out.println("***************************************************************");
        System.out.println("Statistics:");
        System.out.println("- Remaining HP: " + p.getHp());
        System.out.println("- Total Rounds: " + rounds);
        ArrayList<Item> currentlist = new ArrayList<>(p.getInventory());
        ArrayList<Item> initiallist = p.getinitiallist();

        System.out.print("- Items: ");
        for (int i = 0; i < initiallist.size(); i++) {
            Item initial_item = initiallist.get(i);
            int status = 0;

            for (int j = 0; j < currentlist.size(); j++) {
                if (currentlist.get(j).getName().equals(initial_item.getName())) {
                    status += 1;
                }
            }

            System.out.print(initial_item.getName() + ": " + status);
            if (i < initiallist.size() - 1) {
                System.out.print(" | "); // the last item won't need this
            }
        }
    }

    public void displayDefeatScreen(int enemiesLeft, int rounds) {
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("      Defeated. Don't give up, try again!          ");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("Statistics:");
        System.out.println("- Enemies remaining: " + enemiesLeft);
        System.out.println("- Total Rounds Survived: " + rounds);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
    }

    public void displayGameOverMessage(int choice){
        switch(choice) {
            case 1:
                System.out.println("Restarting game with the same settings...");
                break;
            case 2:
                System.out.println("Play Again. Returning to home screen...");
                break;
            case 3:
                System.out.println("Exiting game. Goodbye!");
                break;
        }
    }

    // choose a list of inventory for player at the beginning of the game
    public ArrayList<Item> promptInitialItemSelection(ArrayList<Item> availableItems) {
        ArrayList<Item> selectedItems = new ArrayList<>();
        System.out.println("\n--- INITIAL ITEM SELECTION ---");
        System.out.println("You can choose 2 items:\n");

        while (selectedItems.size() < 2) {
            System.out.println("Available Items:");
            for (int i = 0; i < availableItems.size(); i++) {
                System.out.println((i + 1) + ". " + availableItems.get(i).getName());
            }

            int choice = getValidInput(1, availableItems.size());
            Item chosen = availableItems.get(choice - 1);

            // if (!selectedItems.isEmpty() && chosen.getName().equals(selectedItems.get(0).getName())) {
            //     selectedItems.get(0).setName(chosen.getName() + " A");
            //     chosen.setName(chosen.getName() + " B");
            // }

            selectedItems.add(chosen);
            System.out.println("Added " + chosen.getName() + " to inventory.");
            System.out.println();
        }
        return selectedItems;
    }

    // chooose the character
    public Player promptCharacterSelection(ArrayList<Combatant> templates) {
        System.out.println("Select your character:");
        for (int i = 0; i < templates.size(); i++) {
            Player p = (Player)templates.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " " + p.getStatsSummary()); 
        }
        int choice = getValidInput(1, templates.size());
        return (Player)templates.get(choice - 1); 
    }

    // choose the level
    public LevelManagement promptDifficultySelection(ArrayList<Combatant> templates, ArrayList<LevelManagement> levels) {
        System.out.println("Select Difficulty:");
        for (int i = 0; i < levels.size(); i++) {
            System.out.println((i + 1) + ". " + levels.get(i).getLevelDescription());
        } 
        System.out.println("\nEnemy's attributes:");
        for (int i = 0; i < templates.size(); i++) {
            Enemy e = (Enemy)templates.get(i);
            System.out.println("- " + e.getName() + " " + e.getStatsSummary()); 
        }
        int num = getValidInput(1, levels.size());
        System.out.println();
        return levels.get(num - 1);
    }

    // asking user to choose item for using purpose
    public int promptItemSelection(ArrayList<Item> inventory) {
        System.out.println("Select an Item to use:");
        // displaying the available items in inventory
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).getName());
        }
        System.out.println();
        return getValidInput(1, inventory.size()) - 1; // user only can choose from the inventor list
    }
    // Combatant decides action to take in their turn
    public Action promptActionSelection(Combatant actor, Player player, ArrayList<Enemy> activeEnemies){
        if(actor instanceof Player){
            ArrayList<Action> actions = ((Player)actor).getAvailableActions();
        
            System.out.println("\n--- " + actor.getName() + "'s Turn! Choose action: ---");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println((i + 1) + ". " + actions.get(i).getName());
            }

            int choice = getValidInput(1, actions.size());
            Action selectedAction = actions.get(choice - 1);
            System.out.println();

            if (!selectedAction.isAvailable((Player)actor)) {
                System.out.println(">> " + selectedAction.getName() + " is not available! Please choose again.");
                return promptActionSelection(actor, player, activeEnemies);
            }

            if (selectedAction instanceof UseItem) {
                ArrayList<Item> inventory = ((Player)actor).getInventory();
                int itemIdx = promptItemSelection(inventory); // asking user to choose item
                Item chosenItem = inventory.get(itemIdx);
                ((UseItem)selectedAction).setItem(chosenItem);
            }

            setupActionTargets(selectedAction, player, activeEnemies);

            return selectedAction;
        }
        else{
            Action action = ((Enemy)actor).getAvailableActions().get(0);
            action.addTarget(player);
            return action;
        }
    }

    // matchìng the targettype
    private void setupActionTargets(Action action, Player player, ArrayList<Enemy> enemies) {
        action.clearTargets();
 
        TargetType type = action.getTargetType(player); 

        if (type == TargetType.SINGLE_ENEMY) {
            int idx = promptTargetSelection(enemies);
            System.out.println();
            action.addTarget(enemies.get(idx));
        } 
        else if (type == TargetType.ALL_ENEMIES) {
            action.setTargets(new ArrayList<Combatant>(enemies));
        } 
        else if (type == TargetType.SELF) {
            action.addTarget(player);
        }
    }

    // choose target for attack and special skill
    public int promptTargetSelection(ArrayList<Enemy> targets) {
        System.out.println("Select target:");
        for (int i = 0; i < targets.size(); i++) {
            System.out.printf("%d. %s (HP: %d)\n", (i + 1), targets.get(i).getName(), targets.get(i).getHp());
        }
        return getValidInput(1, targets.size()) - 1;
    }

    public Environment promptEnvironmentSelection(ArrayList<Environment> environments) {
        System.out.println("Select Environment:");
        for (int i = 0; i < environments.size(); i++) {
            System.out.println((i + 1) + ". " + environments.get(i).getEnvironmentDescription());
        }
        int choice = getValidInput(1, environments.size());
        System.out.println();
        return environments.get(choice - 1);
    }

    // when the game is over
    public int promptGameOverOptions() {
        System.out.println("\n--- WHAT WOULD YOU LIKE TO DO? ---");
        System.out.println("1. Replay with the same settings");
        System.out.println("2. Start a new game");
        System.out.println("3. Exit");
        
        return getValidInput(1, 3);
    }

    //method asking user until give an available input
    private int getValidInput(int min, int max) {
        int input;
        while (true) {
            try {
                System.out.print("Enter choice (" + min + "-" + max + "): ");
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) return input;
                System.out.println("Invalid choice. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}