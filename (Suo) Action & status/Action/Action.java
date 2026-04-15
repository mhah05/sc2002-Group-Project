public interface Action {
    void execute(Combatant user, List<Combatant> targets);
    public String getName();
    List<Combatant> getTargets(Combatant user, List<Enemy> enemies, GameUI ui);

}
