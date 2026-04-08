package Items;

import Combatants.Player;

public class Potion extends AbstractItem {

    public Potion(){
        super("Potion");
    }
    //Potion Heals User by 100HP
    public void use(Player user){
        if(isConsumed()) return;

        user.heal(100);
        markConsumed();
    }
    
}
