package engine;

import java.util.ArrayList;
import java.util.List;

import combatants.Enemy;

public class Level 
{
	private int levelNumber;
    private String difficulty;
    private List<Enemy> initialEnemies;
    private List<Enemy> backupEnemies;
    
    // Constructor
    public Level(int levelNumber, String difficulty) 
    {
        this.levelNumber = levelNumber;
        this.difficulty = difficulty;
        this.initialEnemies = new ArrayList<>();
        this.backupEnemies = new ArrayList<>();
    }

    // Getters
    public int getLevelNumber()            { return levelNumber; }
    public String getDifficulty()          { return difficulty; }
    public List<Enemy> getInitialEnemies() { return initialEnemies; }
    public List<Enemy> getBackupEnemies()  { return backupEnemies; }
    public boolean hasBackupSpawn()        { return !backupEnemies.isEmpty(); }
    
    //Setters
    public void setInitialEnemies(List<Enemy> enemies) 
    { 
        this.initialEnemies = enemies; 
    }
    public void setBackupEnemies(List<Enemy> enemies)  
    { 
        this.backupEnemies = enemies; 
    }
}
