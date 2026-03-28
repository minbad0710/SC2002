package Control.LevelManagment;
import Entity.Combatant.Enemy.*;
import java.util.ArrayList;
public class MediumLevel extends LevelManagement{
    @Override
    public ArrayList<Enemy> getInitialSpawns(){
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Goblin("Goblin"));
        enemies.add(new Wolf("Wolf"));
        return enemies;
    }

    @Override
    public boolean hasBackupSpawns(){ 
        return true; 
    }

    @Override
    public ArrayList<Enemy> getBackupSpawns() {
        ArrayList<Enemy> backups = new ArrayList<>();
        backups.add(new Wolf("Wolf A"));
        backups.add(new Wolf("Wolf B"));
        return backups;
    }
}
