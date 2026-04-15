package actions;
public class ShieldBash extends SpecialSkill {
    public ShieldBash() {
        super("Shield Bash");
    }
    public void execute(Combatant user, List<Combatant> targets) {
        if (targets.isEmpty()) {return;}
        Combatant target = targets.get(0);
        target.takeDamage(user.getAttack());
        target.addStatusEffect(new StunEffect());
    }
    
    public List<Combatant> getTargets(Combatant user, List<Combatant> target, GameUI ui){
        List<Combatant> targets = new ArrayList<>();
        if (user instanceof Player) {
            List<Combatant> aliveEnemies = new ArrayList<>();
            for (Combatant c : target) {
                if (c instanceof Enemy && c.isAlive()) {
                    aliveEnemies.add(c);
                }
            }
            if (!aliveEnemies.isEmpty()) {
                Combatant finaltarget = ui.getPlayerTarget(aliveEnemies);
                targets.add(finaltarget);
            }
        }
        return targets;
    }
}
