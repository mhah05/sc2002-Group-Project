package actions;
public class BasicAttack extends AbstractAction{
    public BasicAttack() {
        super("Basic Attack");
    }
    public void execute(Combatant user, List<Combatant> targets) {
        if (targets == null || targets.isEmpty())
            return;
        Combatant target = targets.get(0);
        System.out.println(user.getName() + " -> BasicAttack -> " + target.getName() + ":");
        target.takeDamage(user.getAttack());
    }
    public List<Combatant> getTargets(Combatant user, List<Combatant> target, GameUI ui){
        List<Combatant> targets = new ArrayList<>();
        if (user instanceof Enemy) {
            for (Combatant c : target) {
                if (c instanceof Player && c.isAlive()) {
                    targets.add(c);
                    break;
                }
            }
        }else {
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
