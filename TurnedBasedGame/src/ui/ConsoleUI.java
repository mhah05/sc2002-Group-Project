package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import items.ItemType;
import items.Item;
import engine.GameController;
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
            scanner.nextLine(); 

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
    public void displayBattleStatus(List<combatants.Combatant> combatants) {
        System.out.println("Battle Status:");
        for (combatants.Combatant c : combatants) {
            if (c.isAlive()) {
                System.out.println("- " + c.getName() + " HP: " + c.getCurrentHp() + "/" + c.getMaxHp());
            } else {
                System.out.println("- " + c.getName() + " ELIMINATED");
            }
        }
    }

    public void displayLegalActions(List<actions.Action> actions) {
        for (int i = 1; i <= actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i - 1).getName());
        }
    }

    public void displayTargets(List<? extends combatants.Combatant> targets) {
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
    public actions.Action getPlayerAction(List<actions.Action> actions) {
        System.out.println("Choose an action:");
        displayLegalActions(actions);
        int choice = readChoice(1, actions.size());
        return actions.get(choice - 1);
    }

    @Override
    public combatants.Combatant getPlayerTarget(List<combatants.Combatant> targets) {
        System.out.println("Choose a target:");
        displayTargets(targets);
        int choice = readChoice(1, targets.size());
        return targets.get(choice - 1);
    }

    @Override
    public items.Item getPlayerItem(List<items.Item> items) {
        System.out.println("Choose an item:");
        displayItems(items);
        int choice = readChoice(1, items.size());
        return items.get(choice - 1);
    }

    @Override
    public boolean confirmExit() {
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
        System.out.println("Congratulations, you have defeated all your enemies.");
        System.out.println("Statistics: Remaining HP: " + stats.getRemainingHp() + "/" + stats.getMaxHp()
            + " | Total Rounds: " + stats.getTotalRounds());
    }

    @Override
    public void displayDefeat(GameStats stats) {
        System.out.println("Defeated. Don't give up, try again!");
        System.out.println("Statistics: Enemies remaining: " + stats.getEnemiesRemaining()
            + " | Total Rounds Survived: " + stats.getTotalRounds());
    }

    @Override
    public void showOpeningScene() {
        System.out.println("""
        WELCOME TO THE GAME!
        Loading Screen...
        - Choose your PLAYER:
          Warrior  HP 260 | ATK 40 | DEF 20 | SPD 30
          Wizard   HP 200 | ATK 50 | DEF 10 | SPD 20
        - Know your ENEMY:
          Goblin   HP 55 | ATK 35 | DEF 15 | SPD 25
          Wolf     HP 40 | ATK 45 | DEF 5  | SPD 35
        - Difficulties: Easy, Medium, Hard
          Easy   (Level 1): Initial 3 Goblins
          Medium (Level 2): Initial 1 Goblin + 1 Wolf, Backup 2 Wolves
          Hard   (Level 3): Initial 2 Goblins, Backup 1 Goblin + 2 Wolves
        - Pick your class, items, and difficulty in setup.
        """);
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
        System.out.println("1. Warrior (High HP/DEF, Special Skill: Shield Bash)");
        System.out.println("2. Wizard  (High ATK, Special Skill: Arcane Blast)");
        int choice = readChoice(1, 2);
        return choice == 1 ? "Warrior" : "Wizard";
    }

    @Override
    public int chooseLevel(int minLevel, int maxLevel) {
        System.out.println("Choose level (" + minLevel + " - " + maxLevel + "):");
        return readChoice(minLevel, maxLevel);
    }

    @Override
    public List<ItemType> chooseStartingItemTypes(List<ItemType> availableItems, int maxChoices) {
        List<ItemType> selected = new ArrayList<>();
        if (availableItems == null || availableItems.isEmpty() || maxChoices <= 0) {
            return selected;
        }

        while (selected.size() < maxChoices) {
            System.out.println("Choose item " + (selected.size() + 1) + " of " + maxChoices + ":");
            for (int i = 0; i < availableItems.size(); i++) {
                System.out.println((i + 1) + ". " + availableItems.get(i).getLabel());
            }
            System.out.println((availableItems.size() + 1) + ". Done");

            int choice = readChoice(1, availableItems.size() + 1);
            if (choice == availableItems.size() + 1) break;

            ItemType pickedType = availableItems.get(choice - 1);
            selected.add(pickedType); 
            System.out.println("Added: " + pickedType.getLabel());
        }

        return selected;
    }


    @Override
    public boolean confirmStart(String playerClass, int level, List<items.Item> items) {
        System.out.println();
        System.out.println("Confirm setup...");
        System.out.println("Player: " + playerClass);
        System.out.println("Level : " + level);
        System.out.println("Items : " + (items == null ? 0 : items.size()));

        if (items != null) {
            for (items.Item item : items) {
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

    @Override
    public void showHowToPlay() {
        System.out.println("""
                HOW TO PLAY

                During Gameplay
                - Defeat your enemies using different action!

                Win / Lose Conditions
                - Defeat all enemies to win.
                - If your HP reaches 0, you lose.
                - Good luck!
                """);
    }
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        GameController controller = new GameController(ui);
        controller.start();
    }
}
