package engine;

import java.util.ArrayList;
import java.util.List;

import combatants.Player;
import combatants.Warrior;
import combatants.Wizard;
import items.Item;
import items.ItemFactory;
import items.ItemType;
import items.Potion;
import items.PowerStone;
import items.SmokeBomb;
import ui.GameUI;

public class GameController {
    private final GameUI ui;
    private boolean running = true;

    public GameController(GameUI ui) {
        this.ui = ui;
    }

    public void start() {
        ui.showOpeningScene();

        while (running) {
            int menuChoice = ui.showMainMenu();
            if (menuChoice == 1) {
                runNewGameFlow();
            } else if (menuChoice == 2) {
                ui.showHowToPlay();
                ui.waitForEnter();
            } else {
                running = !ui.confirmExit();
            }
        }

        ui.displayMessage("Thanks for playing.");
    }

    private void runNewGameFlow() {
        GameConfig config = initializeGameConfig();
        if (config == null) {
            ui.displayMessage("Setup cancelled. Returning to main menu.");
            return;
        }

        String selectedClass = config.getPlayer().getName();
        int selectedLevel = config.getLevel().getLevelNumber();
        List<ItemType> selectedTypes = extractItemTypes(config.getItems());

        boolean backToHome = false;
        while (!backToHome && running) {
            runBattle(config);

            int choice = showCompletionMenu();
            if (choice == 1) {
                config = createGameConfig(selectedClass, selectedLevel, selectedTypes);
            } else if (choice == 2) {
                backToHome = true;
            } else {
                running = false;
            }
        }
    }

    private GameConfig initializeGameConfig() {
        String playerClass = ui.choosePlayerClass();
        int levelNumber = ui.chooseLevel(1, 3);
        List<ItemType> selectedTypes = ui.chooseStartingItemTypes(getDefaultItemTypes(), 2);
        List<Item> selectedItems = buildItemsFromTypes(selectedTypes);

        if (!ui.confirmStart(playerClass, levelNumber, selectedItems)) {
            return null;
        }

        return createGameConfig(playerClass, levelNumber, selectedTypes);
    }

    private GameConfig createGameConfig(String playerClass, int levelNumber, List<ItemType> selectedTypes) {
        Player player = "Warrior".equalsIgnoreCase(playerClass) ? new Warrior() : new Wizard();
        Level level = BattleEngine.buildLevelFromAssignment(levelNumber);
        List<Item> selectedItems = buildItemsFromTypes(selectedTypes);
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

    private List<ItemType> extractItemTypes(List<Item> items) {
        List<ItemType> types = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Potion) {
                types.add(ItemType.POTION);
            } else if (item instanceof SmokeBomb) {
                types.add(ItemType.SMOKE_BOMB);
            } else if (item instanceof PowerStone) {
                types.add(ItemType.POWER_STONE);
            }
        }
        return types;
    }

    private int showCompletionMenu() {
        ui.displayMessage("");
        ui.displayMessage("Game Completion Screen");
        ui.displayMessage("1. Replay with the same settings");
        ui.displayMessage("2. Start a new game (return to home screen)");
        ui.displayMessage("3. Exit");
        return ui.readChoice(1, 3);
    }

    private void runBattle(GameConfig config) {
        for (Item item : config.getItems()) {
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
    }
}
