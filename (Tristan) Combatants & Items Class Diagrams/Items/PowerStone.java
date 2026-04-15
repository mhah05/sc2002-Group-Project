package Items;

import java.util.List;

import Actions.Action;

import Combatants.Combatant;
import Combatants.Player;

public class PowerStone extends AbstractItem{

    public PowerStone(){
        super("Power Stone");
    }
    public void use(Player user){
        Action skill = user.getSpecialSkill();
        skill.execute(user,this.getTargets());
        this.markConsumed();
    }
}
