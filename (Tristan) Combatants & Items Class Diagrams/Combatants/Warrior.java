package Combatants;

public class Warrior extends Player {

    Warrior(){ 
        super("Warrior", 200,40 , 45, 30);
    }

    public List<Action> getAvailableActions(){

        List<Action> actions = new ArrayList<>();

        actions.add(new BasicAttack());
        actions.add(new Defend());
        actions.add(new ItemAction());

        if (this.canUseSpecialSkill()) actions.add(new WarriorSkill());

        return actions;
    }

    public void useSpecialSkill(List<Combatant> targets){

    }
    
}
