package ui;

import java.util.List;

import Actions.Action;
import Combatants.Combatant;
import Items.Item;
import engine.GameStats;

public interface GameUI {
    void displayMessage(String message);
    void displayBattleStatus(List<Combatant> combatants);

    int readChoice(int min, int max);

    Action getPlayerAction(List<Action> actions);
    Combatant getPlayerTarget(List<Combatant> targets);
    Item getPlayerItem(List<Item> items);

    void displayVictory(GameStats stats);
    void displayDefeat(GameStats stats);

    // Setup / opening scene
    void showOpeningScene();
    int showMainMenu(); // 1=Start, 2=How to Play, 3=Exit
    String choosePlayerClass(); // Warrior / Wizard
    int chooseLevel(int minLevel, int maxLevel);
    List<Item> chooseStartingItems(List<Item> availableItems, int maxChoices);
    boolean confirmStart(String playerClass, int level, List<Item> items);
    void waitForEnter();
}
