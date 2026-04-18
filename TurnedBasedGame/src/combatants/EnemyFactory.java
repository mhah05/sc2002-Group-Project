package combatants;

public class EnemyFactory {
    private EnemyFactory() {}

    public static Enemy create(EnemyType type) {
        return switch (type) {
            case GOBLIN -> new Goblin();
            case WOLF -> new Wolf();
        };
    }
}
