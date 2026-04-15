package actions;
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
        System.out.println(user.getName() + " → Item");
        item.use(user);
        
    }
    public List<Combatant> getTargets(Combatant user, List<Combatant> potentialTargets, GameUI ui){ return user;}

}
