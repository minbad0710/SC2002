package Control.Environment;

import Entity.Combatant.*;
import java.util.ArrayList;

public class FireEnvironment extends Environment {
    public FireEnvironment() {
        this.value = 5;
        this.description = "Fire environment: scorches all combatants for " + this.value + " HP at the start of each turn.";
    }


    @Override
    public void applyEnvironmentEffect(Combatant combatant) {
        int previousHP = combatant.getHp();
        combatant.takeDamage(this.value + combatant.getDefend()); // damage is reduced by combatant's defense
        this.message = combatant.getName() + " HP: " + previousHP + " -> " + combatant.getHp() + " (damage from the fire environment!)";
    }

}