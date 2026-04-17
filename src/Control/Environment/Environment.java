package Control.Environment;

import java.util.ArrayList;
import Entity.Combatant.Combatant;

abstract public class Environment {
    protected int value;
    protected String description;
    protected String message;

    public int getEnvironmentValue(){
        return this.value;
    }
    public String getEnvironmentDescription(){
        return this.description;
    }
    public String getResultMessage(){
        return this.message;
    }
    abstract public void applyEnvironmentEffect(Combatant combatant);
}