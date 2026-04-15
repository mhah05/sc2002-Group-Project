package ui;

import Actions.Action;
import Combatants.Combatant;
import engine.GameStats;
import java.util.List;
import java.util.Scanner;

/**
 * Command-line implementation of GameUI.
 * Handles all printing to screen and reading player keyboard input.
 * Contains zero game logic - it only displays and collects input.
 */

public class ConsoleUI implements GameUI
{
	private Scanner scanner = new Scanner(System.in);

    // Display methods:
	
	@Override
    public void displayMessage(String message) 
    {
        System.out.println(message);
    }
    
	@Override
    public void displayBattleStatus(List<Combatant> combatants) 
    {
        System.out.println("\n--- Battle Status ---");
        for (Combatant c : combatants) 
        {
            if (c.isAlive()) 
            {
                System.out.println(c.getName() + " HP: " + c.getCurrentHp() + "/" + c.getMaxHp());
            } else 
            {
                System.out.println(c.getName() + " [ELIMINATED]");
            }
        }
        System.out.println("---------------------");
    }

    @Override
    public void displayVictory(GameStats stats) 
    {
        System.out.println("\n================================");
        System.out.println("          VICTORY!                  ");
        System.out.println("================================");
        System.out.println("Congratulations, you have defeated all your enemies.");
        System.out.println("Remaining HP : " + stats.getRemainingHp() + "/" + stats.getMaxHp());
        System.out.println("Total Rounds : " + stats.getTotalRounds());
    }

    @Override
    public void displayDefeat(GameStats stats) 
    {
        System.out.println("\n================================");
        System.out.println("          DEFEATED                  ");
        System.out.println("================================");
        System.out.println("Defeated. Don't give up, try again!");
        System.out.println("Enemies Remaining  : " + stats.getEnemiesRemaining());
        System.out.println("Total Rounds Survived : " + stats.getTotalRounds());
    }

    // Input methods:

    @Override
    public Action getPlayerAction(List<Action> actions) 
    {
        System.out.println("\nChoose your action:");
        for (int i = 0; i < actions.size(); i++) 
        {
            System.out.println((i + 1) + ". " + actions.get(i).getName());
        }
        int choice = readIntInRange(1, actions.size());
        return actions.get(choice - 1);
    }

    @Override
    public Combatant getPlayerTarget(List<Combatant> targets) 
    {
        System.out.println("\nChoose a target:");
        for (int i = 0; i < targets.size(); i++) 
        {
            System.out.println((i + 1) + ". " + targets.get(i).getName()
                    + " (HP: " + targets.get(i).getCurrentHp() + ")");
        }
        int choice = readIntInRange(1, targets.size());
        return targets.get(choice - 1);
    }

    // Helper:

    // Keeps asking until the player types a valid number in range 
    private int readIntInRange(int min, int max) 
    {
        while (true) 
        {
            try 
            {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) return input;
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
