package Items;

import java.util.List;

import Combatants.Combatant;
import Combatants.Player;

public class SmokeBomb extends AbstractItem{

    public SmokeBomb(){
        super("Smoke Bomb");
    }

    //Add targets from itemAction

    public void use(Player user,List<Combatant> targets){
        if (isConsumed()) return;
        user.addStatusEffect(new SmokeBombEffect());
        markConsumed();
        

    }
}
