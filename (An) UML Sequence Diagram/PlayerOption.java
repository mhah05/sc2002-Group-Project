public class PlayerOption {
    private Action action;
    private Combatant target;
    private Item item;
    private boolean exit;

    public PlayerOption(Action action, Combatant target, Item item, boolean exit) {
        this.action = action;
        this.target = target;
        this.item = item;
        this.exit = exit;
    }
    
    public Action getAction() {
        return action;
    }
    
    public Target getTarget() {
        return target;
    }
    
    public Item getItem() {
        return item;
    }
    
    public boolean isExit() {
        return exit;
    }
}
