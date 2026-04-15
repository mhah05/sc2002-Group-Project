package combatants;

import java.util.ArrayList;
import java.util.List;

import items.Item;
import actions.Action;

public abstract class Player extends Combatant{
    
    private List<Item> items = new ArrayList<>();
    private int specialSkillCooldown;

    public Player(String name, int maxHp, int attack, int defence, int speed){
        super(name, maxHp, attack, defence, speed);
        this.specialSkillCooldown = 0;
    }

    public boolean canUseSpecialSkill() {return this.specialSkillCooldown == 0;}

    //After Use of special Skill Cooldown start (length: 3 Turns including current)
    public void resetCooldown() {this.specialSkillCooldown = 2;}

    public void tickCooldown() {if (this.specialSkillCooldown > 0) this.specialSkillCooldown--;}

    public void addItem(Item item){this.items.add(item);}

    public List<Item> getAvailableItems(){
        List<Item> availableItems = new ArrayList<>();

        for(Item item : this.items){
            if(!item.isConsumed()){
                availableItems.add(item);
            }
        }

        return availableItems;
    }

    public abstract Action getSpecialSkill();

}


