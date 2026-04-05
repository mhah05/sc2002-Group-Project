public interface Action {
    void execute(Combatant user, List<Combatant> targets);
    public String getName();
}
