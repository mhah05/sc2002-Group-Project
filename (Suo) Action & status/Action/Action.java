package actions;
import java.util.List;
import combatants.Combatant;
import ui.GameUI;
public interface Action {
    void execute(Combatant user, List<Combatant> targets);
    public String getName();
    List<Combatant> getTargets(Combatant user, List<Combatant> target, GameUI ui);

}
