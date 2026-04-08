package Combatants;

import java.util.ArrayList;
import java.util.List;
import items.Item;

public abstract class Player extends Combatant{
    
    private List<Item> items = new ArrayList<>();
    private int specialSkillCooldown;

    public Player(String name, int maxHp, int attack, int defence, int speed){
        super(name, maxHp, attack, defence, speed);
        this.specialSkillCooldown = 0;
    }

    public boolean canUseSpecialSkill() {return this.specialSkillCooldown == 0;}

    public void resetCooldown() {this.specialSkillCooldown = 0;}

    public void tickCooldown() {if (this.specialSkillCooldown > 0) this.specialSkillCooldown--;}

    public abstract void useSpecialSkill(List<Combatant> targets);
}


