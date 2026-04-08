package Items;

import Combatants.Player;

public class PowerStone extends AbstractItem{

    PowerStone(){
        super("Power Stone");
    }

    public void use(Player user){

        Action skill = user.getSpecialSkill();
        skill.execute(user);
        this.markConsumed();

    }

}
