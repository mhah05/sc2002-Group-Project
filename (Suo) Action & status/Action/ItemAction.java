package actions;
import java.util.List;
import combatants.Combatant;
import ui.GameUI;
import items.Item;
public class ItemAction extends AbstractAction {
    private Item item;
    public ItemAction(Item item) {
        super(item.getName());
        this.item = item;
    }
    public void execute(Combatant user, List<Combatant> targets) {
        if (item.isConsumed()) {
            System.out.println(item.getName() + " is already used up!");
            return;
        }
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
