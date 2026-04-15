package items;

import combatants.Player;
import statuseffects.SmokeBombEffect;

public class SmokeBomb extends AbstractItem{

    public SmokeBomb(){
        super("Smoke Bomb");
    }

    public void use(Player user){
        user.addStatusEffect(new SmokeBombEffect());
        this.markConsumed();
    }
}
