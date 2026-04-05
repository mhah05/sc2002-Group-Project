public void takeDamage(int rawAttackPower) {
    //draft
    int bonusDefense = 0;
    for (StatusEffect effect : statusEffects) {
        if (effect.getName().equals("Defend")) {
            bonusDefense = 10;
            break;
        }
    }
    int totalDefense = this.defense + bonusDefense;
    int finalDamage = Math.max(0, rawAttackPower - totalDefense);
    for (StatusEffect effect : statusEffects) {
        if (effect.getName().equals("SmokeBomb")) {
            finalDamage=0;
            break;
        }
    }
    this.currentHp = Math.max(0, this.currentHp - finalDamage);
    System.out.println(this.name + " (Defense: " + totalDefense + ") takes "
            + finalDamage + " damage.");
    //update HP,isAlive and print the HP decrease and the damage calculation
    //eliminated and check alive will be under the game engine
}
