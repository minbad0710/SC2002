package Control.Environment;

import Entity.Combatant.*;

public class WaterEnvironment extends Environment {
    public WaterEnvironment(){
        this.value = 10;
        this.description = "Water environment: heals all combatants for " + this.value + " HP at the start of each turn.";
    }

    @Override
    public void applyEnvironmentEffect(Combatant combatant) {
        int previousHP = combatant.getHp();
        combatant.heal(this.value);
        this.message = combatant.getName() + " HP: " + previousHP + " -> " + combatant.getHp() + " (healed from the water environment!)";
    }
}