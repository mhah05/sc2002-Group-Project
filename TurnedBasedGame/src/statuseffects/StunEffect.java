package statuseffects;
import combatants.Combatant;
public class StunEffect extends AbstractStatusEffect {
    public StunEffect() {
        super("StunEffect", 2);
    }
    public void apply(Combatant target){
        System.out.println(target.getName() + " STUNNED (2 turns)");
        //this case the target will be the enemy
        }
}
