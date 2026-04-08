package Combatants;

import java.util.ArrayList;
import java.util.List;

public class Goblin extends Enemy {

    public Goblin(){
        super("Goblin",55,35,15,25);
    }

    public List<Action> getAvailableActions(){

        List<Action> actions = new ArrayList<>();

        actions.add(new BasicAttack());

        return actions;
    }
    
}
