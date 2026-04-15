package engine;

import Combatants.Player;
import Items.Item;
import java.util.List;

public class GameConfig 
{
	private Player selectedPlayer;
    private Level selectedLevel;
    private List<Item> selectedItems;
    
    // Constructor
    public GameConfig(Player player, Level level, List<Item> items) 
    {
        this.selectedPlayer = player;
        this.selectedLevel = level;
        this.selectedItems = items;
    }

    // Getters
    public Player getPlayer()       { return selectedPlayer; }
    public Level getLevel()         { return selectedLevel; }
    public List<Item> getItems()    { return selectedItems; }
}
