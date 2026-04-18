package items;

import java.util.List;
import combatants.Combatant;
import ui.GameUI;
import statuseffects.SmokeBombEffect;

public class SmokeBomb extends AbstractItem{

    public SmokeBomb(){
        super("Smoke Bomb");
    }

    public void use(Combatant user, List<Combatant> allCombatants, GameUI ui) {
        user.addStatusEffect(new SmokeBombEffect());
        this.markConsumed();
    }
}
