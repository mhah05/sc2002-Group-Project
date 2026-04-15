package combatants;

import java.util.ArrayList;
import java.util.List;

import actions.Action;
import actions.BasicAttack;

public class Goblin extends Enemy {

    public Goblin(){
        super("Goblin",55,35,15,25);
    }

    @Override
    public List<Action> getAvailableActions(){

        List<Action> actions = new ArrayList<>();

        actions.add(new BasicAttack());

        return actions;
    }
    
}
