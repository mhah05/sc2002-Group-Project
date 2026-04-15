package items;

import actions.Action;
import actions.BasicAttack;
import combatants.Player;

public class PowerStone extends AbstractItem{

    public PowerStone(){
        super("Power Stone");
    }
    public void use(Player user){
        Action skill = user.getSpecialSkill();
        List<Combatant> targets = skill.getTargets()
        skill.execute(user,BasicAttack.getTargets(user));
        this.markConsumed();
    }
}
