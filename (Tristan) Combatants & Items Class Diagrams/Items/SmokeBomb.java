package Items;

import Combatants.Player;

public class SmokeBomb extends AbstractItem{

    public SmokeBomb(){
        super("Smoke Bomb");
    }

    public void use(Player user){
        if (isConsumed()) return;
        user.addStatusEffect(new SmokeBombEffect());
        markConsumed();
        

    }
}
