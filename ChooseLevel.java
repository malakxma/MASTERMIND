import javax.swing.JOptionPane;
public class ChooseLevel implements GameState{

    @Override
    public void run(GameGUI gui){
        //details on the level differences - choose difficulty popup
        String[] options = {"Easy (4)", "Medium (6)", "Hard (8)"};
        int difficulty = JOptionPane.showOptionDialog(gui,
                "Easy: crack a code of 4 colors, no repeats\n" + //
                                        "Medium: crack a code of 6 colors, repeats allowed\n" + //
                                        "Hard: crack a code of 8 colors, repeats allowed",
                "Mastermind Difficulty",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (difficulty == -1) System.exit(0);
        //create a game based on the difficulty selected
        GameLevel level = GameLevelFactory.create(difficulty + 1);
        Game game = new Game(level);
        game.registerObserver(gui);
        gui.setGame(game);
        gui.setChances(game.getMaxChances());
        gui.setState(new PlayingState());
    }
}