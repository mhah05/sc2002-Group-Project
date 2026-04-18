package combatants;

public enum EnemyType {
    GOBLIN("Goblin"),
    WOLF("Wolf");

    private final String label;

    EnemyType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
