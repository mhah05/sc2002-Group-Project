public class DefendEffect extends AbstractStatusEffect {
    public DefendEffect() {
        super("Defend", 2);
    }
    public void apply(Combatant target){
        System.out.println(target.getName() + " -> Defend :");
        //this case the target will be user itself
    }
}
