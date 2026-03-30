
import Boundary.GameCLI;
import Control.BattleEngine;
import Control.LevelManagment.*;
import Control.TurnOrderStrategy.*;
import Entity.Combatant.Player.*;


public class Main{
    public static void main(String[] args) {
        GameCLI ui = new GameCLI(); // create the UI instance
        LevelManagement level = null; // create the LevelManagement instance
        TurnOrderStrategy turnStrategy = new SpeedOrderStrategy(); // create the TurnOrderStrategy instance
        int choice = ui.promptCharacterSelection(); // prompt the user to select a character
        Player player = null; // create the Player instance
        switch (choice) { // create the Player instance based on the user's choice
            case 1:
                player = new Warrior(); // create a Warrior instance
                break;
            case 2:
                player = new Wizard(); // create a Wizard instance
                break;
        };

        player.setInventory(ui.promptInitialItemSelection()); // prompt the user to select initial items and set the player's inventory
        choice = ui.promptDifficultySelection(); // prompt the user to select a level
        switch (choice) { // create the LevelManagement instance based on the user's choice
            case 1:
                level = new EasyLevel(); // create an EasyLevelManagement instance
                break;
            case 2:
                level = new MediumLevel(); // create a MediumLevelManagement instance
                break;
            case 3:
                level = new HardLevel(); // create a HardLevelManagement instance
                break;
        };
        BattleEngine engine = new BattleEngine(player, level, turnStrategy, ui); // create the BattleEngine instance with the created instances
        engine.startBattle(); // start the battle
    }
}