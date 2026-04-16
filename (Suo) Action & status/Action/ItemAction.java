package actions;
import java.util.ArrayList;
import java.util.List;
import combatants.Combatant;
import ui.GameUI;
import items.Item;
import combatants.Player;
public class ItemAction extends AbstractAction {
    private Item selectedItem;
    private List<Combatant> allTargets;
    private GameUI savedUI;
    
    public ItemAction() {
        super(item.getName());
    }
    public List<Combatant> getTargets(Combatant user, List<Combatant> allTargets, GameUI ui){
        this.allTargets = allTargets;
        this.savedUI = ui;
        
        List<Combatant> targets = new ArrayList<>();
        Player player = (Player) user;
        List<Item> items = player.getItems();
        if (items.isEmpty()) {
            System.out.println("No available items!");
            return targets;
        }
        this.selectedItem = ui.getPlayerItem(items);
        targets.add(user);
        return targets;
    }
    public void execute(Combatant user, List<Combatant> target) {
        //check available
        if (selectedItem == null) return;
        if (item.isConsumed()) {
            System.out.println(item.getName() + " is already used up!");
            return;
        }
        //decide item to use
        System.out.println(user.getName() + " → Item → " + selectedItem.getName + " used:");
        selectedItem.use(user,this.allTargets,this.savedUI);
    }
}
