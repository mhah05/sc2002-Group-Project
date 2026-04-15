package actions;
import java.util.List;
import combatants.Combatant;
import ui.GameUI;
public abstract class SpecialSkill extends AbstractAction {
    public SpecialSkill(String name) {
        super(name);
    }
    public abstract List<Combatant> getTargets(Combatant user, List<Combatant> enemies, GameUI ui);

}
