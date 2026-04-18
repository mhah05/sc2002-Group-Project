package combatants;

import java.util.List;

import actions.BasicAttack;
import actions.Defend;
import actions.ItemAction;
import actions.ShieldBash;
import actions.Action;

import java.util.ArrayList;

public class Warrior extends Player {

    public Warrior(){ 
        super("Warrior", 260,40 , 20, 30);
    }

    @Override
    public Action getSpecialSkill(){return new ShieldBash();}

    @Override
    public List<Action> getAvailableActions(){

        List<Action> actions = new ArrayList<>();
        
        actions.add(new BasicAttack());
        actions.add(new Defend());
        actions.add(new ItemAction());

        if (this.canUseSpecialSkill()) actions.add(this.getSpecialSkill());

        return actions;
    }
    
}
