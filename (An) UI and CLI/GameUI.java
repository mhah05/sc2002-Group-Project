package ui;

import java.util.List;

import engine.GameStats;

public interface GameUI {
    void displayMessage(String message);
    void displayBattleStatus(List combatants);

    int readChoice(int min, int max);

    actions.Action getPlayerAction(List actions);
    combatants.Combatant getPlayerTarget(List targets);
    items.Item getPlayerItem(List items);

    void displayVictory(GameStats stats);
    void displayDefeat(GameStats stats);

    // Setup / opening scene
    void showOpeningScene();
    int showMainMenu(); // 1=Start, 2=How to Play, 3=Exit
    String choosePlayerClass(); // Warrior / Wizard
    int chooseLevel(int minLevel, int maxLevel);
    java.util.List<items.ItemType> chooseStartingItemTypes(
        java.util.List<items.ItemType> availableTypes,
        int maxChoices
    );
    boolean confirmStart(String playerClass, int level, List items);
    void waitForEnter();
}
