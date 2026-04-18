package items;

import java.util.List;

import ui.GameUI;
import actions.Action;
import combatants.Combatant;
import combatants.Player;
import actions.SpecialSkill;

public class PowerStone extends AbstractItem{

    public PowerStone(){
        super("Power Stone");
    }
    public void use(Combatant user, List<Combatant> allCombatants, GameUI ui) {
        if (user instanceof Player) {
            Player player = (Player) user;
            Action specialSkill = player.getSpecialSkill();
            if (specialSkill != null) {
                this.markConsumed();
                List<Combatant> targets = specialSkill.getTargets(user, allCombatants, ui);
                specialSkill.execute(player, targets);
            }
        }
    }
}
