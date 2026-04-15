package statuseffects;
public class DefendEffect extends AbstractStatusEffect {
    public DefendEffect() {
        super("DefendEffect", 2);
    }
    public void apply(Combatant target){
        System.out.println(target.getName() + " -> Defend！");
        //this case the target will be user itself
    }
}
