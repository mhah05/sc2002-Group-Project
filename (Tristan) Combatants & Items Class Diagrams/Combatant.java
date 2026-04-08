package Combatants;

import java.util.ArrayList;
import java.util.List;


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

        public void takeDamage(int amount){
            int damage = Math.max(0, amount - this.defence);
            this.currentHp = Math.max(0, this.currentHp - damage);
        }

        public void heal(int amount) {this.currentHp = Math.min(amount+this.currentHp,this.maxHp);}

        public boolean isAlive() {return this.currentHp > 0;}

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


