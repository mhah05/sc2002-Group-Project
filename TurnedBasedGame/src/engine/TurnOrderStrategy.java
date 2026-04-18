package engine;

import java.util.List;

import combatants.Combatant;

/**
 * Strategy interface for determining turn order each round.
 * BattleEngine depends on this abstraction, not on SpeedBasedOrder directly.
 * This demonstrates DIP - the engine never references a concrete ordering class.
 * New ordering strategies (e.g. random order) can be added without touching BattleEngine.
 */

public interface TurnOrderStrategy 
{
	List<Combatant> determineTurnOrder(List<Combatant> combatants);
}
