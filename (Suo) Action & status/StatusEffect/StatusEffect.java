package statuseffects;
import combatants.Combatant;
public interface StatusEffect {
    public void apply(Combatant target);
    public boolean isExpired();
    public String getName();
}
