package items;

public class ItemFactory {
    private ItemFactory() {}

    public static Item create(ItemType type) {
        return switch (type) {
            case POTION -> new Potion();
            case SMOKE_BOMB -> new SmokeBomb();
            case POWER_STONE -> new PowerStone();
        };
    }
}
