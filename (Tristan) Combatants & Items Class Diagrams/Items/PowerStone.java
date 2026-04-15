package items;

import actions.Action;
import actions.BasicAttack;
import combatants.Player;

public class PowerStone extends AbstractItem{

    public PowerStone(){
        super("Power Stone");
    }
    public void use(Player user){
        Action specialSkill = player.getSpecialSkill();
        this.markConsumed();
    }
}
