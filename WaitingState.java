import java.util.*;

public class WaitingState implements GameState{
    public GameController controller;
    public Scanner scanner;
    public WaitingState(GameController control, Scanner scan){
        this.controller = control;
        this.scanner = scan;
    }
    @Override
    public void run(){
        System.out.println("Generating feedback on your password...");
    };
    
}
