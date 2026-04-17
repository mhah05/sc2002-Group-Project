package statuseffects;
import combatants.Combatant;
public class SmokeBombEffect extends AbstractStatusEffect {
    public SmokeBombEffect() {
        super("SmokeBombEffect", 2);
    }
    public void apply(Combatant target){
        System.out.println(" Enemy attacks deal 0 damage this turn + next");
        //this case the target will be user itself
    }
}

