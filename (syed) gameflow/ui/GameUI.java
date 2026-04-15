package ui;

import Actions.Action;
import Combatants.Combatant;
import engine.GameStats;
import java.util.List;

/**
 * Interface defining what any UI must be able to show and ask.
 * BattleEngine only talks to this interface, never to ConsoleUI directly.
 * Meaning a GUI version could replace ConsoleUI without touching BattleEngine.
 */

public interface GameUI 
{
	// Display a plain text message to the screen 
    void displayMessage(String message);

    // Show the current HP and status of all combatants 
    void displayBattleStatus(List<Combatant> combatants);

    // Show available actions and return the one the player chose 
    Action getPlayerAction(List<Action> actions);

    // Show alive enemies and return the one the player targeted 
    Combatant getPlayerTarget(List<Combatant> targets);

    // Show the victory screen with final stats 
    void displayVictory(GameStats stats);

    // Show the defeat screen with final stats
    void displayDefeat(GameStats stats);
}
