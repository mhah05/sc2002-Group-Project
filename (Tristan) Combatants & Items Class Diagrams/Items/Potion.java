package items;

import java.util.List;
import combatants.Combatant;
import ui.GameUI;

public class Potion extends AbstractItem {

    public Potion(){
        super("Potion");
    }
    //Potion Heals User by 100HP
    public void use(Combatant user, List<Combatant> allCombatants, GameUI ui){
        user.heal(100);
        this.markConsumed();
    }
}
