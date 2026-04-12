package Combatants;

import java.util.List;
import java.util.ArrayList;

public class Wizard extends Player{

    public Wizard(){
        super("Wizard", 200, 50, 10, 20);
    }
    //Add setAttack() for wizard effect


    @override
    public Action getSpecialSkill(){return new WizardSkill();}

    public List<Action> getAvailableActions(){

        List<Action> actions = new ArrayList<>();
        actions.add(new BasicAttack());
        actions.add(new Defend());
        actions.add(new ItemAction());
        
        if (this.canUseSpecialSkill()) actions.add(this.getSpecialSkill());

        return actions;

    }
    
}
