package Combatants;

import java.util.ArrayList;
import java.util.List;

public class Wolf extends Enemy {

    public Wolf(){
        super("Wolf",40,45,5,35);
    }

    public List<Action> getAvailableActions(){

        List<Action> actions = new ArrayList<>();

        actions.add(new BasicAttack());

        return actions;
    }
    
}
