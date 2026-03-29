package Entity.Item;

import Entity.Combatant.Combatant;
import java.util.ArrayList;

public class Potion extends Item{
    public Potion(){
        this.name ="Potion";
        this.description = "Heal 100HP";
    }
    
    @Override
    public Item recreate(){
        return new Potion();
    }
    @Override
    public void use(Combatant actor, ArrayList <Combatant> targets){
        actor.heal(100);
    }
}
