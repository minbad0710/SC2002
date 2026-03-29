package Control.LevelManagment;
import Entity.Combatant.Enemy.*;
import java.util.ArrayList;
public class EasyLevel extends LevelManagement{
    @Override
    public ArrayList<Enemy> getInitialSpawns(){
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Goblin("Goblin A"));
        enemies.add(new Goblin("Goblin B"));
        enemies.add(new Goblin("Goblin C"));
        return enemies;
    }

    @Override
    public boolean hasBackupSpawns(){ 
        return false; 
    }

    @Override
    public ArrayList<Enemy> getBackupSpawns(){ 
        return new ArrayList<>(); /*return empty list here */
    }

}
