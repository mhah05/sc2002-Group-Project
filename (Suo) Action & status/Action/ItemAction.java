package actions;
import java.util.List;
import combatants.Combatant;
import ui.GameUI;
import items.Item;
public class ItemAction extends AbstractAction {
    public ItemAction() {
        super(item.getName());
    }
    public void execute(Combatant user, List<Combatant> targets) {
        //check available
        if (item.isConsumed()) {
            System.out.println(item.getName() + " is already used up!");
            return;
        }
        //decide item to use
        ui.getPlayerItem();
        System.out.println(user.getName() + " → Item");
        item.use(user);
        
    }
    public List<Combatant> getTargets(Combatant user, List<Combatant> target, GameUI ui){
        List<Combatant> targets = new ArrayList<>();
        targets.add(user);
        return targets;
    }

}
