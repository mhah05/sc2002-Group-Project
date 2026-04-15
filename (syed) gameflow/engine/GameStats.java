package engine;

// Holds end-of-game statistics to pass to GameUI for display.

public class GameStats 
{
	private int remainingHp;
    private int maxHp;
    private int totalRounds;
    private int enemiesRemaining; // only relevant on defeat
    
    // Constructor
    public GameStats(int remainingHp, int maxHp, int totalRounds, int enemiesRemaining) 
    {
        this.remainingHp = remainingHp;
        this.maxHp = maxHp;
        this.totalRounds = totalRounds;
        this.enemiesRemaining = enemiesRemaining;
    }
    
    // Getters
    public int getRemainingHp()     { return remainingHp; }
    public int getMaxHp()           { return maxHp; }
    public int getTotalRounds()     { return totalRounds; }
    public int getEnemiesRemaining(){ return enemiesRemaining; }
}
