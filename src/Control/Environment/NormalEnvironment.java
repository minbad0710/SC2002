package Control.Environment;

import Entity.Combatant.*;

public class NormalEnvironment extends Environment {
    public NormalEnvironment(){
        this.value = 0;
        this.description = "Normal environment: has no effect on combatants at the start of each turn.";
    }

    @Override
    public void applyEnvironmentEffect(Combatant combatant) {
        // No effect
        this.message = "";
    }
}