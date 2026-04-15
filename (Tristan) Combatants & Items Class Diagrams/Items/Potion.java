package items;


import combatants.Player;

public class Potion extends AbstractItem {

    public Potion(){
        super("Potion");
    }
    //Potion Heals User by 100HP
    public void use(Player user){
        user.heal(100);
        this.markConsumed();
    }
}
