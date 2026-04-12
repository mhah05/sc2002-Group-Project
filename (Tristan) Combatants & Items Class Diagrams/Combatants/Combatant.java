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

        public int getAttack() {return this.attack;}

        public abstract List<Action> getAvailableActions();

        // Applies incoming damage after accounting for defense.
        // Final damage = max(0, incomingAmount - defense).
        // HP is reduced but never goes below 0.    
        public void takeDamage(int amount){
            int damage = Math.max(0, amount - this.defence);
            this.currentHp = Math.max(0, this.currentHp - damage);
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
}


