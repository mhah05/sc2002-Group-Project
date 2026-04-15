package actions;
import java.util.List;
import combatants.Combatant;
import ui.GameUI;
import items.Item;
import combatants.Player;
public class ItemAction extends AbstractAction {
    private Item selectedItem;
    public ItemAction() {
        super(item.getName());
    }
    public List<Combatant> getTargets(Combatant user, List<Combatant> target, GameUI ui){
        List<Combatant> targets = new ArrayList<>();
        Player player = (Player) user;
        List<Item> items = player.getItems();
        if (inventory.isEmpty()) {
            System.out.println("No available items!");
            return targets;
        }
        this.selectedItem = ui.getPlayerItem(items);
        targets.add(user);
        return targets;
    }
    public void execute(Combatant user, List<Combatant> targets) {
        //check available
        if (selectedItem == null) return;
        if (item.isConsumed()) {
            System.out.println(item.getName() + " is already used up!");
            return;
        }
        //decide item to use
        System.out.println(user.getName() + " → Item");
        selectedItem.use(user);
    }
}
