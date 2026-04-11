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
}