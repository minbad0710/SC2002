import java.util.ArrayList;

import Boundary.BattleInPutUI;
import Boundary.BattleOutPutUI;
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
import Control.Environment.*;

public class Main{
    public static void main(String[] args) {
        BattleInPutUI input = new GameCLI(); 
        BattleOutPutUI output = new GameCLI(); 
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

        ArrayList<Environment> environments = new ArrayList<>();
        environments.add(new FireEnvironment());
        environments.add(new WaterEnvironment());
        environments.add(new NormalEnvironment());

        while(running){
            Player selectedTemplate = input.promptCharacterSelection(playerTemplates);
        
            ArrayList<Item> initialItems = input.promptInitialItemSelection(itemPool);
            LevelManagement selectedLevel = input.promptDifficultySelection(enemyTemplates, levelTemplates);
            Environment selectedEnvironment = input.promptEnvironmentSelection(environments);
            
            boolean matchRunning = true;
            while(matchRunning) {
                Player player = selectedTemplate.clonePlayer();
                player.setInventory(new ArrayList<>(initialItems)); 
                
                LevelManagement current_level = selectedLevel.cloneLevel();
                
                TurnOrderStrategy turnStrategy = new SpeedOrderStrategy();
                BattleEngine engine = new BattleEngine(player, current_level, turnStrategy, input, output, selectedEnvironment);
                
                int result = engine.startBattle();
                output.displayGameOverMessage(result);
                switch (result) {
                    case 1: // play a new game with the same game settings
                        break;
                    case 2: // play a new game with new game settings
                        matchRunning = false;
                        break;
                    case 3: // Exit
                        matchRunning = false;
                        running = false;
                        break;
                }
            }
        }
    }
}