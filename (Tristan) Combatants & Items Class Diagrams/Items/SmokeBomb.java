package Items;

import java.util.List;

import Combatants.Combatant;
import Combatants.Player;

public class SmokeBomb extends AbstractItem{

    public SmokeBomb(){
        super("Smoke Bomb");
    }

    public void use(Player user){
        user.addStatusEffect(new SmokeBombEffect());
        this.markConsumed();
    }
}
