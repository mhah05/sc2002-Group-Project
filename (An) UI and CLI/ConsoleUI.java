package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import items.ItemFactory;
import items.ItemType;
import combatants.Enemy;
import combatants.Goblin;
import combatants.Player;
import combatants.Warrior;
import combatants.Wizard;
import combatants.Wolf;
import engine.BattleEngine;
import engine.GameConfig;
import items.Item;
import items.Potion;
import items.PowerStone;
import items.SmokeBomb;
import engine.GameStats;
import engine.Level;
import engine.SpeedBasedOrder;

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
    public void displayBattleStatus(List combatants) {
        System.out.println("Battle Status:");
        for (Object obj : combatants) {
            combatants.Combatant c = (combatants.Combatant) obj;
            System.out.println("- " + c.getName() + " HP: " + c.getCurrentHp() + "/" + c.getMaxHp());
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
    public actions.Action getPlayerAction(List actions) {
        System.out.println("Choose an action:");
        List<actions.Action> actionList = (List<actions.Action>) actions;
        displayLegalActions(actionList);
        int choice = readChoice(1, actionList.size());
        return actionList.get(choice - 1);
    }

    @Override
    public combatants.Combatant getPlayerTarget(List targets) {
        System.out.println("Choose a target:");
        List<combatants.Combatant> targetList = (List<combatants.Combatant>) targets;
        displayTargets(targetList);
        int choice = readChoice(1, targetList.size());
        return targetList.get(choice - 1);
    }

    @Override
    public items.Item getPlayerItem(List items) {
        System.out.println("Choose an item:");
        List<items.Item> itemList = (List<items.Item>) items;
        displayItems(itemList);
        int choice = readChoice(1, itemList.size());
        return itemList.get(choice - 1);
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
        System.out.println("Welcome to ");
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
    public boolean confirmStart(String playerClass, int level, List items) {
        List<items.Item> typedItems = (List<items.Item>) items;
        System.out.println();
        System.out.println("Confirm setup");
        System.out.println("Player: " + playerClass);
        System.out.println("Level : " + level);
        System.out.println("Items : " + (typedItems == null ? 0 : typedItems.size()));

        if (typedItems != null) {
            for (items.Item item : typedItems) {
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

    private void showHowToPlay() {
        System.out.println("How To Play");
        System.out.println("- Pick a class and level.");
        System.out.println("- Each round, choose an action.");
        System.out.println("- Defeat all enemies to win.");
    }

    private GameConfig initializeGameConfig() {
        String playerClass = choosePlayerClass();
        int levelNumber = chooseLevel(1, 3);
        List<ItemType> selectedTypes = chooseStartingItemTypes(getDefaultItemTypes(), 2);
        List<Item> selectedItems = buildItemsFromTypes(selectedTypes);
        if (!confirmStart(playerClass, levelNumber, selectedItems)) {
            return null;
        }

        Player player = "Warrior".equalsIgnoreCase(playerClass) ? new Warrior() : new Wizard();
        Level level = buildLevel(levelNumber);

        return new GameConfig(player, level, selectedItems);

    }

    private List<ItemType> getDefaultItemTypes() {
    return List.of(ItemType.POTION, ItemType.SMOKE_BOMB, ItemType.POWER_STONE);
}

    private List<Item> buildItemsFromTypes(List<ItemType> selectedTypes) {
        List<Item> items = new ArrayList<>();
        for (ItemType type : selectedTypes) {
            items.add(ItemFactory.create(type)); 
        }
        return items;
    }


    private Level buildLevel(int levelNumber) {
        Level level = new Level(levelNumber, "Normal");
        List<Enemy> initialEnemies = new ArrayList<>();
        List<Enemy> backupEnemies = new ArrayList<>();

        if (levelNumber == 1) {
            initialEnemies.add(new Goblin());
            initialEnemies.add(new Wolf());
        } else if (levelNumber == 2) {
            initialEnemies.add(new Goblin());
            initialEnemies.add(new Goblin());
            initialEnemies.add(new Wolf());
            backupEnemies.add(new Wolf());
        } else {
            initialEnemies.add(new Wolf());
            initialEnemies.add(new Wolf());
            initialEnemies.add(new Goblin());
            backupEnemies.add(new Goblin());
            backupEnemies.add(new Wolf());
        }

        level.setInitialEnemies(initialEnemies);
        level.setBackupEnemies(backupEnemies);
        return level;
    }

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.showOpeningScene();

        boolean running = true;
        while (running) {
            int menuChoice = ui.showMainMenu();
            if (menuChoice == 1) {
                GameConfig config = ui.initializeGameConfig();
                if (config == null) {
                    ui.displayMessage("Setup cancelled. Returning to main menu.");
                    continue;
                }

                for (items.Item item : config.getItems()) {
                    config.getPlayer().addItem(item);
                }
                     
                BattleEngine engine = new BattleEngine(
                        config.getPlayer(),
                        config.getLevel().getInitialEnemies(),
                        new SpeedBasedOrder(),
                        ui,
                        config.getLevel()
                );
                engine.startBattle();
                ui.waitForEnter();
            } else if (menuChoice == 2) {
                ui.showHowToPlay();
                ui.waitForEnter();
            } else {
                running = !ui.userExit();
            }
        }

        ui.displayMessage("Thanks for playing.");
    }
}
