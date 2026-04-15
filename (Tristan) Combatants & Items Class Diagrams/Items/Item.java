package Items;

import java.util.List;

import Combatants.Combatant;
import Combatants.Player;

public interface Item{

    public String getName();
    public void use(Player user);

}
