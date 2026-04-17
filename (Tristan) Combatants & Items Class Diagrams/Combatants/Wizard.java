package combatants;

import java.util.List;
import java.util.ArrayList;

import actions.ArcaneBlast;
import actions.BasicAttack;
import actions.ItemAction;
import actions.Defend;
import actions.Action;

public class Wizard extends Player{

    public Wizard(){
        super("Wizard", 200, 50, 10, 20);
    }

    @Override
    public Action getSpecialSkill(){return new ArcaneBlast();}

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
