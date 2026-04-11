public abstract class AbstractAction implements Action {
    protected String name;

    public AbstractAction(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public abstract void execute(Combatant user, List<Combatant> targets);
}