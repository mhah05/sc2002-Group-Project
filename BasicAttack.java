public class BasicAttack extends AbstractAction{
    public BasicAttack() {
        super("Basic Attack");
    }
    public void execute(Combatant user, List<Combatant> targets) {
        if (targets == null || targets.isEmpty())
            return;
        Combatant target = targets.get(0);
        int rawDamage = user.getAttack()-target.getDefense();
        int finalDamage = Math.max(0,rawDamage);
        System.out.println(user.getName() + " -> BasicAttack -> " + target.getName() + ":");
        target.takeDamage(finalDamage);
    }
}
