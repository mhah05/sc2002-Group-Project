/* things to do:
- display battle state
- display legal actions
- read user choice 
- ask for target if needed
- ask for item if item chosen
- confirm exit
- send result to engine 
whimsy:
ascii for
title screen
character selection header
round banners
victory/defeat screens*/
import java.util.Scanner;
import java.util.List;
public class ConsoleUI implements GameUI {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public int readChoice(int min, int max) {
        while (true) {
            System.out.print("> ");

            if(!scanner.hasNextInt()) {
                System.out.println("Numbers only, brave one. Try again."
               );
               scanner.next();
               continue;
            }

            int choice = scanner.nextInt();

            if (choice < min || choice > max) {
                System.out.println("Choose between " + min + "and " + max + ".");
                continue;
            }
            return choice;
        }
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayBattleState(BattleState state) {
        System.out.println("Battle State:");
    }

    @Override
    public void displayLegalActions(List<Action> actions) {
        for (int i = 1; i <= actions.size();i++) {
            System.out.println(i + ". " + actions.get(i-1).getName());
        }
     }

    public Action askActionChoice(List<Action> actions) {
        for (int i = 1; i <= actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i-1).getName());
        }
        int choice = readChoice(1, actions.size());
        return actions.get(choice-1);
    }

    public Combatant askTargetChoice(List<? extends Combatant> targets) {
        for (int i = 1; i <= targets.size(); i++) {
            System.out.println(i + ". " + targets.get(i-1).getName());
        }

        int choice = readChoice(1, targets.size());
        return targets.get(choice-1); 
    }

    public Item askItemChoice(List<Item> items) {
        for (int i = 1; i <= items.size(); i++) {
            System.out.println(i + ". " + items.get(i-1).getName());
        }

        int choice = readChoice(1, items.size());
        return items.get(choice-1);
    }

    public boolean userExit() {
        while(true) {
            System.out.println("Are you sure you want to exit? y for YES, n for NO.");
            
            String userInput = scanner.next();

            if (userInput.equals("y")) {
                return true;
            }
            if (userInput.equals("n")) {
                return false;
            }
            else {
                System.out.println("y or n only.");
            }
        }
    }

    @Override
    public PlayerOption playerTurn (BattleState state, List<Action> legalActions, List<? extends Combatant> validTargets, List<Item> usableItems) {
        while (true) {
            displayBattleState(state);
            displayLegalActions(legalActions);

            Action action = askActionChoice(legalActions);

            if (action.getName().equals("Exit")) {
                if (userExit()) {
                    return new PlayerOption(action, null, null, true);
                }
                else {
                    continue;
                }
            }

            Combatant chosenTarget = null;
            /* haven't add code for asking for validTargets and ask for item */

            return new PlayerOption(action, null, null, false);
        }
    }

    
}
