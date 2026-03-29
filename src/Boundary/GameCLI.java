package Boundary;

import Entity.Combatant.Combatant;
import Entity.Combatant.Enemy.Enemy;
import Entity.Item.Item;
import Entity.Item.Potion;
import Entity.Item.PowerStone;
import Entity.Item.SmokeBomb;

import java.util.ArrayList;
import java.util.Scanner;

public class GameCLI {
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

    public void displayBattleStatus(ArrayList<Combatant> players, ArrayList<Enemy> enemies) {
        System.out.println("\n--- BATTLE STATUS ---");
        for (Combatant p : players) {
            System.out.printf("[Player] %s | HP: %d/%d | ATK: %d | DEF: %d | SPD: %d\n",
                p.getName(), p.getHp(), p.getMaxHP(), p.getAttack(), p.getDefend(), p.getSpeed());
        }
        System.out.println("---------------------");
        for (Enemy e : enemies) {
            System.out.printf("[Enemy] %s | HP: %d | ATK: %d | DEF: %d | SPD: %d\n",
                e.getName(), e.getHp(), e.getAttack(), e.getDefend(), e.getSpeed());
        }
        System.out.println("---------------------\n");
    }

    public void displayTurnResult(String message) {
        System.out.println(">> " + message);
    }

    public void displayVictoryScreen(int hp, int rounds) {
        System.out.println("\n****************************************");
        System.out.println("      CONGRATULATIONS! VICTORY!         ");
        System.out.println("****************************************");
        System.out.println("Statistics:");
        System.out.println("- Remaining HP: " + hp);
        System.out.println("- Total Rounds: " + rounds);
        System.out.println("****************************************\n");
    }

    public void displayDefeatScreen(int enemiesLeft, int rounds) {
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("      DEFEATED. DON'T GIVE UP!          ");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("Statistics:");
        System.out.println("- Enemies remaining: " + enemiesLeft);
        System.out.println("- Total Rounds Survived: " + rounds);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
    }
    /*asking user to choose*/
    // choose a list of inventory for player at the beginning of the game
    public ArrayList<Item> promptInitialItemSelection() {
        ArrayList<Item> selectedItems = new ArrayList<>(); // create an empty list to add items in
        Item[] available = { new Potion(), new PowerStone(), new SmokeBomb() }; // list of available items
        
        System.out.println("\n--- INITIAL ITEM SELECTION ---");
        System.out.println("Choose 2 items for your adventure:");
        
        while (selectedItems.size() < 2) {
            System.out.println("\nAvailable Items:");
            // display the available items
            for (int i = 0; i < available.length; i++) {
                String status = selectedItems.contains(available[i]) ? "[SELECTED]" : ""; // this will show if user has selected it before
                System.out.printf("%d. %s %s\n", (i + 1), available[i].getName(), status);
            }
            
            int choice = getValidInput(1, 3);
            Item chosen = available[choice - 1];
            
    
            selectedItems.add(chosen);
            System.out.println("Added " + chosen.getName() + " to inventory.");
        }
        return selectedItems;
    }
    // chooose the character
    public int promptCharacterSelection() {
        System.out.println("Select your character:");
        System.out.println("1. Warrior (HP: 260, ATK: 40, DEF: 20, SPD: 30)");
        System.out.println("2. Wizard  (HP: 200, ATK: 50, DEF: 10, SPD: 20)"); 
        return getValidInput(1, 2);
    }
    // choose the level
    public int promptDifficultySelection() {
        System.out.println("Select Difficulty:");
        System.out.println("1. Easy   (3 Goblins)");
        System.out.println("2. Medium (1 Goblin, 1 Wolf + 2 Wolf Backup)");
        System.out.println("3. Hard   (2 Goblins + 1 Goblin, 2 Wolf Backup)"); 
        return getValidInput(1, 3);
    }
    // asking user to choose item for using purpose
    public int promptItemSelection(ArrayList<Item> inventory) {
        System.out.println("Select an Item to use:");
        // displaying the available items in inventory
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).getName());
        }
        return getValidInput(1, inventory.size()) - 1; // user only can choose from the inventor list
    }
    // player decides action to take in their turn
    public int promptActionSelection(int maxOption) {
        System.out.println("Your turn! Choose action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        System.out.println("4. Special Skill");
        return getValidInput(1, maxOption);
    }
    // choose target for attack and special skill
    public int promptTargetSelection(ArrayList<Enemy> targets) {
        System.out.println("Select target:");
        for (int i = 0; i < targets.size(); i++) {
            System.out.printf("%d. %s (HP: %d)\n", (i + 1), targets.get(i).getName(), targets.get(i).getHp());
        }
        return getValidInput(1, targets.size()) - 1;
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