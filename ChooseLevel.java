
import java.util.*;
public class ChooseLevel implements GameState{
    // public GameController controller;

    // public ChooseLevel(GameController control){
    //     this.controller = control;
    // }
    public void run(){
        System.out.print("Choose a difficulty: 1(Easy), 2(Medium), 3(Hard) -> ");
        Scanner scanner = new Scanner(System.in);
        int difficulty = scanner.nextInt();
        scanner.close();
        if (!(0<difficulty && difficulty<4)){
            throw new IllegalArgumentException("Your response must be 1, 2, or 3");
        }
        System.out.printf("Creating game level of difficulty %d\n", difficulty);

        // GameLevel level = new GameLevel();
    };
    
}
