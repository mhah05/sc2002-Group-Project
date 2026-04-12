package Items;

import java.util.List;

import javax.swing.Action;

import Combatants.Combatant;
import Combatants.Player;

public class PowerStone extends AbstractItem{

    PowerStone(){
        super("Power Stone");
    }

    public void use(Player user,List<Combatant> targets){

        Action skill = user.getSpecialSkill();
        skill.execute(user);
        this.markConsumed();

    }

}
