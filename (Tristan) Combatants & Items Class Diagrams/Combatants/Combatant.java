package Combatants;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;




public abstract class Combatant {

        //Combatant Attributes
        private String name;
        private int maxHp;
        private int currentHp;
        private int attack;
        private int defence;
        private int speed; 
        //Array for StatusEffect (eg: stun)
        private List<StatusEffect> statusEffects = new ArrayList<>();

        //CONSTRUCTOR
        public Combatant(String name, int maxHp, int attack, int defence, int speed){
            this.name = name;
            this.maxHp = maxHp;
            this.currentHp = maxHp;
            this.attack = attack;
            this.defence = defence;
            this.speed = speed;
        }

        //Getters
        public String getName() {return this.name;}
        public int getAttack() {return this.attack;}
        public int getSpeed() {return this.speed;}
        public int getCurrentHp() {return this.currentHp;}
        public int getMaxHp() {return this.maxHp;}
        public List<StatusEffect> getStatusEffects() {return this.statusEffects;} 
        public abstract List<Action> getAvailableActions();

        //Setters
        public void setAttack(int increase) {this.attack+=increase;}


        public void takeDamage(int rawAttackPower) {
            //draft
            int bonusDefence = 0;
            for (StatusEffect effect : this.statusEffects) {
                if (effect.getName().equals("DefendEffect")) {
                    bonusDefence = 10;
                    break;
                }
            }
            int totalDefence = this.defence + bonusDefence;
            int finalDamage = Math.max(0, rawAttackPower - totalDefence);
            for (StatusEffect effect : statusEffects) {
                if (effect.getName().equals("SmokeBombEffect")) {
                    finalDamage=0;
                    System.out.print("(Smoke Bomb active)");
                    break;
                }
            }
            this.currentHp = Math.max(0, this.currentHp - finalDamage);
            System.out.println(this.name + " (Defence: " + totalDefence + ") takes "
                    + finalDamage + " damage." + " ");

            //Example: Goblin (Defence: X) takes X damage. (dmg: finalDamage calculations)
            
            //update HP,isAlive and print the HP decrease and the damage calculation
            //eliminated and check alive will be under the game engine

            //PRINT THE UPDATE (eg: HP: 215 → 200 (dmg: 35−20=15))
        }

        // Restores HP by the given amount.
        // New HP = min(currentHp + amount, maxHp).
        // HP cannot exceed the maximum HP.
        public void heal(int amount) {this.currentHp = Math.min(amount+this.currentHp,this.maxHp);}

        public boolean isAlive() {return this.currentHp > 0;}

        //Add effect to (statusEffects array)
        //apply to combatant using the .apply method
        public void addStatusEffect(StatusEffect effect){ 

            this.statusEffects.add(effect);
            effect.apply(this);
        }
        
        public void removeExpiredEffects(){
           for (int i = statusEffects.size() - 1; i >= 0; i--){
                if (statusEffects.get(i).isExpired()) statusEffects.remove(i);
           }
        }

        public void updateStatusEffects() {
            for (StatusEffect effect : statusEffects) {
                effect.decrementDuration();
            }
            this.removeExpiredEffects();
        }

}

