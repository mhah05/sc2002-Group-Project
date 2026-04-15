package engine;

import combatants.Combatant;
import combatants.Player;
import combatants.Enemy;
import ui.GameUI;
import actions.Action;
import statuseffects.StatusEffect;
import java.util.ArrayList;
import java.util.List;

/**
 * BattleEngine depends only on abstractions:
 *   - Combatant (not Warrior or Goblin)
 *   - TurnOrderStrategy (not SpeedBasedOrder)
 *   - GameUI (not ConsoleUI)
 * This is to demonstrate DIP throughout.
 */

public class BattleEngine 
{
	private Player player;
	private List<Enemy> enemies;
	private TurnOrderStrategy turnOrderStrategy;
	private GameUI ui;
	
	private int roundNumber = 0;
	private boolean backupSpawned = false;
	private Level currentLevel;
	
	//Constructor
	public BattleEngine(Player player, List<Enemy> enemies, 
			TurnOrderStrategy turnOrderStrategy, GameUI ui)
	{
		this.player = player;
		this.enemies = new ArrayList<>(enemies);
		this.turnOrderStrategy = turnOrderStrategy;
		this.ui = ui;
	}
	
	// Overloaded constructor that also takes a Level for backup spawn support
	public BattleEngine(Player player, List<Enemy> enemies, 
			TurnOrderStrategy turnOrderStrategy, GameUI ui, 
			Level currentLevel)
	{
		this(player, enemies, turnOrderStrategy, ui);
		this.currentLevel = currentLevel;
	}
	
	
	// Starts and runs the full battle until win or loss
	public void startBattle()
	{
		ui.displayMessage("=== Battle Start! ===");
		
		while (!isGameOver())
		{
			roundNumber++;
			ui.displayMessage("\n========== Round " + roundNumber + " ==========");
			
			// Step 1: Determine turn order form alive combatants only
			List<Combatant> aliveCombatants = getAliveCombatants();
			List<Combatant> turnOrder = turnOrderStrategy.determineTurnOrder(aliveCombatants);
			
			// Step 2: Tick and clean status effects BEFORE anyone acts
			for (Combatant c : aliveCombatants)
			{
				c.updateStatusEffects();
			}
			
			// Step 3: Process each combatant's turn in order
			for (Combatant c : turnOrder)
			{
				// Skip if killed earlier this round
				if (!c.isAlive()) continue;
				
				// Skip if stunned - stunned combatants DONT get cooldown ticked
				if (isStunned(c))
				{
					ui.displayMessage(c.getName() + " is STUNNED - turn skipped.");
					continue;
				}
				
				// Execute the turn
				processTurn(c);
				
				// Tick cooldown only if the combatant actually took a turn
				if (c instanceof Player)
				{
					((Player) c).tickCooldown();
				}
				
				// Check if game ended mid-round
				if (isGameOver()) break;
				
				// Check if backup wave should spawn
				checkBackupSpawn();
			}
			
			// Display full status at end of round
			ui.displayBattleStatus(getAllCombatants());
		}
		
		//Battle over - show result
		displayEndResult();
	}
	
	// The Core Methods:
	
	/**
	 * Processes one combatant's turn. 
	 * Player gets a menu choice. Enemy always uses BasicAttack.
	 */
	public void processTurn(Combatant combatant)
	{
		if (combatant instanceof Player)
		{
			playerTurn((Player) combatant);
		}
		else
		{
			enemyTurn(combatant);
		}
	}
	
	// To handle a player's turn: show action menu, get target, execute.
	private void playerTurn(Player player)
	{
		// Get available actions (includes SpecialSkill only if cooldown == 0)
		List<Action> actions = player.getAvailableActions();
		Action chosen = ui.getPlayerAction(actions);
		
		// Determine target lost based on the action chosen
		List<Combatant> targets = chosen.getTargets(player, getAllCombatants(), ui);
		chosen.execute(player, targets);
	}
	
	// To handle an enemy's turn: always BasicAttack on the player.
	private void enemyTurn(Combatant enemy)
	{
		Action action = enemy.getAvailableActions().get(0); // enemies always do BasicAttack
		List<Combatant> targets = action.getTargets(enemy, getAllCombatants(), ui);
		action.execute(enemy, targets);
	}
    
    // Win/Loss checking:
    
    /** 
     * Returns true if battle has ended 
     * (player dead OR all enemies dead with no backup)
     */
    public boolean isGameOver()
    {
    	if (!player.isAlive()) return true;
    	
    	boolean allEnemiesDead = enemies.stream().noneMatch(Combatant::isAlive);
    	boolean noBackupLeft = backupSpawned 		// backup already happened
    			|| (currentLevel == null) 			// no level defined at all
    			|| !currentLevel.hasBackupSpawn(); 	// level has no backup wave
    	
    	return allEnemiesDead && noBackupLeft;
    }
    
    // Returns "Player" if player wins, "Enemies" if player loses.
    public String getWinner()
    {
    	if(!player.isAlive()) return "Enemies";
    	return "Player";
    }
    
    // Backup spawn:
    
    /**
     * Checks if all current enemies are defeated and a backup wave is available.
     * If so, spawns all backup enemies simultaneously.
     */
    private void checkBackupSpawn()
    {
    	if (backupSpawned || (currentLevel == null) 
    			|| !currentLevel.hasBackupSpawn()) return;
    	
    	boolean allCurrentDead = enemies.stream().noneMatch(Combatant::isAlive);
    	
    	if (allCurrentDead)
    	{
    		// NOTE: requires Level class to be made as Level.getBackupEnemies() is used
    		List<Enemy> backup = currentLevel.getBackupEnemies();
    		enemies.addAll(backup);
    		backupSpawned = true;
    		
    		ui.displayMessage("\n*** BACKUP SPAWN! ***");
    		for(Enemy e : backup)
    		{
    			ui.displayMessage(e.getName() + " has entered the battle!");
    		}
    	}	
    }
    
    // Status effect helpers:
    
    // Returns true if the combatant has am active (non-expired) Stun effect.
    private boolean isStunned (Combatant c)
    {
    	for (StatusEffect effect : c.getStatusEffects())
    	{
            if (effect.getName().equals("StunEffect") && !effect.isExpired()) 
            {
                return true;
            }
        }
        return false;
    }

    // Utility helpers:

    // Returns a combined list of player + all enemies.
    private List<Combatant> getAllCombatants() 
    {
        List<Combatant> all = new ArrayList<>();
        all.add(player);
        all.addAll(enemies);
        return all;
    }

    // Returns only the alive combatants from all.
    private List<Combatant> getAliveCombatants() 
    {
        List<Combatant> alive = new ArrayList<>();
        if (player.isAlive()) alive.add(player);
        for (Enemy e : enemies) 
        {
            if (e.isAlive()) alive.add(e);
        }
        return alive;
    }

    // Displays the appropriate victory or defeat screen.
    private void displayEndResult() 
    {
        int enemiesRemaining = (int) enemies.stream().filter(Combatant::isAlive).count();
        
        GameStats stats = new GameStats(
                player.getCurrentHp(),
                player.getMaxHp(),
                roundNumber,
                enemiesRemaining
        );

        if (!player.isAlive()) 
        {
            ui.displayDefeat(stats);
        } 
        else 
        {
            ui.displayVictory(stats);
        }
    }
    
}
