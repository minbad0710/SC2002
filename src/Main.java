import java.util.ArrayList;

import Boundary.BattleUI;
import Boundary.GameCLI;
import Control.BattleEngine;
import Control.LevelManagment.*;
import Control.TurnOrderStrategy.*;
import Entity.Combatant.Combatant;
import Entity.Combatant.Enemy.Goblin;
import Entity.Combatant.Enemy.Wolf;
import Entity.Combatant.Player.*;
import Entity.Item.Item;
import Entity.Item.Potion;
import Entity.Item.PowerStone;
import Entity.Item.SmokeBomb;

public class Main{
    public static void main(String[] args) {
        BattleUI ui = new GameCLI(); 
        boolean running = true;

        ArrayList<Combatant> playerTemplates = new ArrayList<>();
        playerTemplates.add(new Warrior());
        playerTemplates.add(new Wizard());

        ArrayList<Combatant> enemyTemplates = new ArrayList<>();
        enemyTemplates.add(new Goblin("Goblin"));
        enemyTemplates.add(new Wolf("Wolf"));

        ArrayList<LevelManagement> levelTemplates = new ArrayList<>();
        levelTemplates.add(new EasyLevel());
        levelTemplates.add(new MediumLevel());
        levelTemplates.add(new HardLevel());

        ArrayList<Item> itemPool = new ArrayList<>();
        itemPool.add(new Potion());
        itemPool.add(new PowerStone());
        itemPool.add(new SmokeBomb());

        while(running){
            Player selectedTemplate = ui.promptCharacterSelection(playerTemplates);
        
            ArrayList<Item> initialItems = ui.promptInitialItemSelection(itemPool);
            LevelManagement selectedLevel = ui.promptDifficultySelection(enemyTemplates, levelTemplates);
            
            boolean matchRunning = true;
            while(matchRunning) {
                Player player = selectedTemplate.clonePlayer();
                player.setInventory(new ArrayList<>(initialItems)); 
                
                LevelManagement current_level = selectedLevel.cloneLevel();
                
                TurnOrderStrategy turnStrategy = new SpeedOrderStrategy();
                BattleEngine engine = new BattleEngine(player, current_level, turnStrategy, ui);
                
                int result = engine.startBattle();
                
                switch (result) {
                    case 1: // play a new game with the same game settings
                        ui.displayRestartMessage();
                        break;
                    case 2: // play a new game with new game settings
                        ui.displayReturnHomeMessage();
                        matchRunning = false;
                        break;
                    case 3: // Exit
                        ui.displayExitMessage();
                        matchRunning = false;
                        running = false;
                        break;
                }
            }
        }
    }
}