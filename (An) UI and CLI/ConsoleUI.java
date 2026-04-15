package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Actions.Action;
import Combatants.Combatant;
import Items.Item;
import engine.GameStats;

public class ConsoleUI implements GameUI {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int readChoice(int min, int max) {
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextInt()) {
                System.out.println("Numbers only. Try again.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume trailing newline

            if (choice < min || choice > max) {
                System.out.println("Choose between " + min + " and " + max + ".");
                continue;
            }
            return choice;
        }
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayBattleStatus(List<Combatant> combatants) {
        System.out.println("Battle Status:");
        for (Combatant c : combatants) {
            System.out.println("- " + c.getName() + " HP: " + c.getCurrentHp() + "/" + c.getMaxHp());
        }
    }

    public void displayLegalActions(List<Action> actions) {
        for (int i = 1; i <= actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i - 1).getName());
        }
    }

    public void displayTargets(List<? extends Combatant> targets) {
        for (int i = 1; i <= targets.size(); i++) {
            System.out.println(i + ". " + targets.get(i - 1).getName());
        }
    }

    public void displayItems(List<Item> items) {
        for (int i = 1; i <= items.size(); i++) {
            System.out.println(i + ". " + items.get(i - 1).getName());
        }
    }

    @Override
    public Action getPlayerAction(List<Action> actions) {
        System.out.println("Choose an action:");
        displayLegalActions(actions);
        int choice = readChoice(1, actions.size());
        return actions.get(choice - 1);
    }

    @Override
    public Combatant getPlayerTarget(List<Combatant> targets) {
        System.out.println("Choose a target:");
        displayTargets(targets);
        int choice = readChoice(1, targets.size());
        return targets.get(choice - 1);
    }

    @Override
    public Item getPlayerItem(List<Item> items) {
        System.out.println("Choose an item:");
        displayItems(items);
        int choice = readChoice(1, items.size());
        return items.get(choice - 1);
    }

    public boolean userExit() {
        while (true) {
            System.out.println("Are you sure you want to exit? y for YES, n for NO.");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equals("y")) {
                return true;
            }
            if (userInput.equals("n")) {
                return false;
            }

            System.out.println("y or n only.");
        }
    }

    @Override
    public void displayVictory(GameStats stats) {
        System.out.println("Victory!");
        System.out.println("Rounds: " + stats.getTotalRounds());
        System.out.println("HP Remaining: " + stats.getRemainingHp() + "/" + stats.getMaxHp());
    }

    @Override
    public void displayDefeat(GameStats stats) {
        System.out.println("Defeat.");
        System.out.println("Rounds: " + stats.getTotalRounds());
        System.out.println("Enemies remaining: " + stats.getEnemiesRemaining());
    }

    @Override
    public void showOpeningScene() {
        System.out.println("===========================================");
        System.out.println("      SC2002 TURN-BASED COMBAT ARENA       ");
        System.out.println("===========================================");
        System.out.println("Two heroes. Multiple waves. One survivor.");
        System.out.println();
    }

    @Override
    public int showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Start New Game");
        System.out.println("2. How To Play");
        System.out.println("3. Exit");
        return readChoice(1, 3);
    }

    @Override
    public String choosePlayerClass() {
        System.out.println("Choose your player:");
        System.out.println("1. Warrior (High HP/DEF, Shield Bash)");
        System.out.println("2. Wizard  (High ATK, Arcane Blast)");
        int choice = readChoice(1, 2);
        return choice == 1 ? "Warrior" : "Wizard";
    }

    @Override
    public int chooseLevel(int minLevel, int maxLevel) {
        System.out.println("Choose level (" + minLevel + " - " + maxLevel + "):");
        return readChoice(minLevel, maxLevel);
    }

    @Override
    public List<Item> chooseStartingItems(List<Item> availableItems, int maxChoices) {
        List<Item> selected = new ArrayList<>();
        if (availableItems == null || availableItems.isEmpty() || maxChoices <= 0) {
            return selected;
        }

        while (selected.size() < maxChoices) {
            System.out.println("Choose item " + (selected.size() + 1) + " of " + maxChoices + ":");
            for (int i = 0; i < availableItems.size(); i++) {
                Item item = availableItems.get(i);
                System.out.println((i + 1) + ". " + item.getName());
            }
            System.out.println((availableItems.size() + 1) + ". Done");

            int choice = readChoice(1, availableItems.size() + 1);
            if (choice == availableItems.size() + 1) {
                break;
            }

            Item picked = availableItems.get(choice - 1);
            selected.add(picked);
            System.out.println("Added: " + picked.getName());
        }

        return selected;
    }

    @Override
    public boolean confirmStart(String playerClass, int level, List<Item> items) {
        System.out.println();
        System.out.println("===== Confirm Setup =====");
        System.out.println("Player: " + playerClass);
        System.out.println("Level : " + level);
        System.out.println("Items : " + (items == null ? 0 : items.size()));

        if (items != null) {
            for (Item item : items) {
                System.out.println("- " + item.getName());
            }
        }

        System.out.println();
        System.out.println("Start battle?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        return readChoice(1, 2) == 1;
    }

    @Override
    public void waitForEnter() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
