package items;

import java.util.List;
import combatants.Combatant;
import ui.GameUI;

public interface Item{

    public String getName();
    public void use(Combatant user, List<Combatant> allTargets, GameUI ui);
    public boolean isConsumed();

}
