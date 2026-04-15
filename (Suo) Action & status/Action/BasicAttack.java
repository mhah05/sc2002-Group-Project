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
}
