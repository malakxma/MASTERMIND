import java.util.*;

import javax.swing.JOptionPane;
public class PlayingState implements GameState{
    public void run(GameGUI gui){
        gui.setGuess(new String[gui.getGame().getLength()]);
        gui.prepGUI();
    }
    public void handleGuess(GameGUI gui){
        //get the colors and positions of current guess
        Map<Integer, String> selections = gui.getCurrentSelections();
        String[] guess = new String[gui.getGame().getLength()];

        //check if color selection and expected password length is equal
        if (selections.size() != guess.length) {
            JOptionPane.showMessageDialog(gui, "Please make a selection for each slot.", "Incomplete Guess", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //put guess in the correct order
        for (int i = 0; i < guess.length; i++) {
            guess[i] = selections.get(i);
        }

        try {
            //declare the guess
            gui.setGuess(guess);

            //notify obserbvers of guess to validate colors, collect feedback, and return feedback
            gui.getGame().notifyObservers(guess);
            gui.clearSelection();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(gui, ex.getMessage(), "Invalid Guess", JOptionPane.ERROR_MESSAGE);
        }
    
    }
}
