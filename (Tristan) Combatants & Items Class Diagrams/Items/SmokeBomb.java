package Items;

import Combatants.Player;

public class SmokeBomb extends AbstractItem{

    public SmokeBomb(){
        super("Smoke Bomb");
    }

    //Add targets from itemAction

    public void use(Player user){
        if (isConsumed()) return;
        user.addStatusEffect(new SmokeBombEffect());
        markConsumed();
        

    }
}
