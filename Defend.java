public class Defend extends AbstractAction {
    public Defend() {
        super("Defend");
    }
    public void execute(Combatant user, List<Combatant> targets) {
        user.addStatusEffect(new DefendEffect());
    }
}