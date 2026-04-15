package Combatants;

import java.util.List;

import Action.ArcaneBlast;
import Action.BasicAttack;
import Action.Defend;
import Action.ItemAction;

import java.util.ArrayList;

public class Wizard extends Player{

    public Wizard(){
        super("Wizard", 200, 50, 10, 20);
    }

    public void setAttack(int finalAttack ){this.attack = finalAttack;}

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
