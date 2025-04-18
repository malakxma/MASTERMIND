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
            System.out.println("You won!");
            System.out.print("This is the correct password: "+Arrays.toString(secret));
        }
        else{
            System.out.println("You lose");
            System.out.print("This is the correct password: "+ Arrays.toString(secret));
        }

        System.out.print("Do you want to play again? (y/n): ");
        String response = scanner.nextLine();
        if (response.strip().equals("y")){
            controller.setState(new ChooseLevel(controller, scanner));
        }
        else{
            System.out.println("Thanks for playing!");
            controller.setState(null);
        }

    };
}
