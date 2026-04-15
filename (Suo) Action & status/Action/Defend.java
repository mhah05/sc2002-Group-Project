package actions;
import java.util.ArrayList;
import java.util.List;
import combatants.Combatant;
import ui.GameUI;
import statuseffects.DefendEffect;
public class Defend extends AbstractAction {
    public Defend() {
        super("Defend");
    }
    public void execute(Combatant user, List<Combatant> targets) {
        user.addStatusEffect(new DefendEffect());
    }
    public List<Combatant> getTargets(Combatant user, List<Combatant> target, GameUI ui) {
        // targets always the user
        List<Combatant> targets = new ArrayList<>();
        targets.add(user);
        return targets;
    }
}
