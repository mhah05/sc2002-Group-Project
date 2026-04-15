package statuseffect;

public abstract class AbstractStatusEffect implements StatusEffect {
    private String name;
    private int duration;
    public AbstractStatusEffect(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
    public String getName() {
        return this.name;
    }
    public boolean isExpired() {
        return duration <= 0;
    }
    public void decrementDuration() {
        if (duration > 0) {
            duration--;
        }
    }
    public void apply(Combatant target){}
}
