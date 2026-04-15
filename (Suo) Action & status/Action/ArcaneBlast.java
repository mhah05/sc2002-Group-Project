public class ArcaneBlast extends SpecialSkill {
    public ArcaneBlast() {
        super("Arcane Blast");
    }
    public void execute(Combatant user, List<Combatant> targets) {
        int enemiesKilled = 0;
        for (Combatant target : targets) {
            if (!target.isAlive()) {continue;}
            target.takeDamage(user.getAttack());
            if (!target.isAlive()) {
                enemiesKilled++;
            }
        }
        if (enemiesKilled > 0) {
            int buffAmount = enemiesKilled * 10;
            int finalAttack = user.getAttack()+buffAmount;
            System.out.println("ATK: "+user.getAttack()+" → "+finalAttack+" (+10 per Arcane Blast kill)");
            user.setAttack(finalAttack);
            //to be added under Wizard/player class;
        }
    }
    public List<Combatant> getTargets(Combatant user, List<Combatant> target, GameUI ui) {
        List<Combatant> targets = new ArrayList<>();
        for (Combatant c : target) {
            if (c instanceof Enemy && c.isAlive()) {
                targets.add(c);
            }
        }
        return targets;
    }
}
