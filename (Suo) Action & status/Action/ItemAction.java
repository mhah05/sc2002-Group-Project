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

        //in the item.use(), we may change to
        item.use(user, targets);
        //so it may be clearer, while user and target may or may not be same



    }
}
