import java.util.*;

public class PlayingState implements GameState{
    public GameController controller;
    public String guess;
    public  Scanner scann;

    public PlayingState(GameController control, Scanner scanner){
        this.controller = control;
        this.scann = scanner;
    }
    @Override
    public void run(){
        boolean win = false;
        Game game = controller.getGame();
        int chances = game.getMaxChances();
        while (chances!=0){
            System.out.println("\nAvailable colors: "+ Arrays.toString(game.level.getAvailableColors()));
            System.out.println("Chances left: "+ chances);
            System.out.print("Enter password guess of " + game.getLength() + " (colors separated by spaces in the order you're guessing): ");
            String input = scann.nextLine().trim().toUpperCase();
            String[] inputArray = input.split("\\s+");
            if (inputArray.length!=game.getLength()){
                throw new IllegalArgumentException("You can only have "+ game.getLength() + " colors");
            }
            for (String colors: inputArray){
                game.validateColor(colors);
            }

            //correct colors
            int correctColors = game.correctColor(inputArray);

            //colors in correct position
            int correctPosition = game.correctColorPos(inputArray);

            //check if password is completely correct
            if (correctColors == game.getLength() && correctPosition == game.getLength()){
                win = true;
            }

            //feedback
            System.out.println("\nYou have " + correctColors + " correct color(s) in the password. \n"+ correctPosition + " of the "+  correctColors+" correct color(s) are in the right position.");

            if (win!=true){
                chances-=1;
            }
            else{
                break;
            }

        }
        controller.setState(new EndingState(controller, win, scann, game.getSecretPassword()));
    };
}
