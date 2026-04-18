package combatants;

public abstract class Enemy extends Combatant{

    Enemy(String name, int maxHp, int attack, int defence, int speed){
        super(name, maxHp, attack, defence, speed);
    }
}