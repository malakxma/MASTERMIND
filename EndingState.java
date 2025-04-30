import java.util.*;

public class EndingState implements GameState{
    public GameController controller;
    public boolean win;
   public Scanner scanner;
   public String[] secret;
    public EndingState(GameController control, boolean won, Scanner scan, String[] password){
        this.controller = control;
        this.win = won;
        this.scanner = scan;
        this.secret = password;

    }
    @Override
    public void run(){
        if (win){
            System.out.println("\nYou won!");
            System.out.print("\nThis is the correct password: "+Arrays.toString(secret));
        }
        else{
            System.out.println("\nYou lose");
            System.out.print("\nThis is the correct password: "+ Arrays.toString(secret));
        }
        //determine if another game takes place
        System.out.print("\nDo you want to play again? (y/n): ");
        String response = scanner.nextLine();
        if (response.strip().equals("y")){
            controller.setState(new ChooseLevel(controller, scanner));
        }
        else{
            System.out.println("\nThanks for playing!");
            //end game loop
            controller.setState(null);
        }

    };
}
