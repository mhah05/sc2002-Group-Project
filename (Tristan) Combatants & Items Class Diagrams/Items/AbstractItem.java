package Items;

public abstract class AbstractItem implements Item{

    private String name;
    private boolean consumed = false;

    public AbstractItem(String name){
        this.name = name;
    }

    public String getName() {return this.name;}

    public boolean isConsumed() {return this.consumed;}

    protected void markConsumed(){this.consumed = true;}
    
}
