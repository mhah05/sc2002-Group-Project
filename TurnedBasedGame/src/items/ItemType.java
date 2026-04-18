package items;

public enum ItemType {
    POTION("Potion"),
    SMOKE_BOMB("Smoke Bomb"),
    POWER_STONE("Power Stone");

    private final String label;
    
    ItemType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
