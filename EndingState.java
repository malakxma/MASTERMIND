import javax.swing.JOptionPane;

public class EndingState implements GameState{
    public final boolean win;
    public EndingState(boolean win){
        this.win = win;
    }
    @Override
    public void run(GameGUI gui){
        //if win, message is guessed the password, else, game over
        String message = win ? "🎉 You guessed the password!" : "💀 Game over!";
        message += "\nThe correct password was: " + String.join(" ", gui.getGame().getSecretPassword());

        //allow for anonther game
        int choice = JOptionPane.showConfirmDialog(gui, message + "\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            gui.setState(new ChooseLevel());
        } else {
            System.exit(0);
        }
    }
}