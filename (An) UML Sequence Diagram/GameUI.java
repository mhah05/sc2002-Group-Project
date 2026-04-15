import java.util.List;
public interface GameUI {
    void displayMessage(String message);

    void displayBattleState(BattleState state);

    void displayLegalActions(List<Action> actions);


    int readChoice(int min, int max);
    
    PlayerOption playerTurn(
        BattleState state,
        List<Action> legalActions,
        List<? extends Combatant> validTargets,
        List<Item> usableItems
    );

}
