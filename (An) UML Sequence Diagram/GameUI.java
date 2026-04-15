import java.util.List;

import Combatants.Combatant;
import Items.Item;
public interface GameUI {
    void displayMessage(String message);

    void displayBattleState(BattleState state);

    


    int readChoice(int min, int max);
    
    void displayLegalActions(List<Action> actions);
    Action getPlayerAction(List<Action> actions);

    void displayTargets(List<? extends Combatant> targets);
    Combatant getPlayerTarget(List<? extends Combatant> targets);

    void displayItems(List<Item> items);
    Item getPlayerItem(List<Item> items);

    PlayerOption playerTurn(
        BattleState state,
        List<Action> legalActions,
        List<? extends Combatant> validTargets,
        List<Item> usableItems
    );

}
