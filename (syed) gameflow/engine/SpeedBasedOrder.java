package engine;

import Combatants.Combatant;
import java.util.ArrayList;
import java.util.List;

public class SpeedBasedOrder implements TurnOrderStrategy
{
	 @Override
   public List<Combatant> determineTurnOrder(List<Combatant> combatants) 
	{
        List<Combatant> ordered = new ArrayList<>(combatants);
        ordered.sort((a, b) -> b.getSpeed() - a.getSpeed());
        return ordered;
  }
}
