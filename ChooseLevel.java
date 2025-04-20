
import java.util.*;
public class ChooseLevel implements GameState{
    public GameController controller;
    public Scanner scanner;
    public ChooseLevel(GameController control, Scanner scan){
        this.controller = control;
        this.scanner = scan;
        
    }
    @Override
    public void run(){
        // GameLevel level;
        int difficulty;
        System.out.println("\nEasy: crack a code of 4 colors, no repeats\nMedium: crack a code of 6 colors, repeats allowed\nHard: crack a code of 8 colors, repeats allowed");
        System.out.print("Choose a difficulty: 1(Easy), 2(Medium), 3(Hard) -> ");
        String input = scanner.nextLine();
        // System.out.println("Got input: "+ input);
        try {
            difficulty = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input! Please enter 1, 2, or 3.");
            return;
        }
        GameLevel level = GameLevelFactory.create(difficulty);


        //switch(difficulty){
            //case 1:
                //level = new EasyLevel();
               // break;
            //case 2:
        //         level = new MediumLevel();
        //         break;
        //     case 3:
        //         level = new HardLevel();
        //         break;
        //     default:
        //         throw new IllegalArgumentException("Your response must be 1, 2, or 3");

        // }


        // if (!(0<difficulty && difficulty<4)){
        //     throw new IllegalArgumentException("Your response must be 1, 2, or 3");
        // }
        System.out.printf("Creating game level of difficulty %d\n", difficulty);
        Game newGame = new Game(level);
        // System.out.println("Got game");

        controller.setGame(newGame);
        // System.out.println("set game");
        // System.out.println("Switching to playing state");
        controller.setState(new PlayingState(controller, scanner));
    };
    
}
