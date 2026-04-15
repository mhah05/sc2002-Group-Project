package statuseffects;
import combatants.Combatant;
public class SmokeBombEffect extends AbstractStatusEffect {
    public SmokeBombEffect() {
        super("SmokeBombEffect", 1);
    }
    public void apply(Combatant target){
        System.out.println(" → Smoke Bomb used: Enemy attacks deal 0 damage this turn + next");
        //this case the target will be user itself
    }
}

