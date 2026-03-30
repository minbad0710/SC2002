package Control.LevelManagment;
import Entity.Combatant.Enemy.*;
import java.util.ArrayList;
public abstract class LevelManagement {
    
    public abstract ArrayList<Enemy> getInitialSpawns();

    public abstract boolean hasBackupSpawns();

    public abstract void changeBackupSpawnStatus(boolean status);

    public abstract ArrayList<Enemy> getBackupSpawns();
    
}
