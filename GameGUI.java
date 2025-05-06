import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class GameGUI extends JFrame implements FeedbackObserver{
    // private GameController controller;
    private Game game;
    private String[] guess;
    private int currentChances;

    private JTextArea feedbackArea;
    private JButton submitButton;

    private JPanel guessPanel;
    private Map<Integer, String> currentSelections = new HashMap<>();
    private final Color SELECTED_BORDER_COLOR = Color.BLACK;
    public GameState currentState;

    //start GUI -> movement to choose level popup
    public GameGUI() {
        setTitle("Mastermind");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setVisible(true);

        setState(new ChooseLevel());
    }

    //change states 
    public void setState(GameState newState){
        this.currentState = newState;
        currentState.run(this);
    }

    //set game
    public void setGame(Game game){
        this.game=game;
    }
    
    //getter for the game at play
    public Game getGame(){
        return this.game;
    }

    public void setGuess(String[] guess){
        this.guess=guess;
    }

    public void setChances(int chance){
        this.currentChances=chance;
    }

    // public int getChance(){
    //     return this.currentChances;
    // }

    //GUI for gameplay -> color sequence, text area, and so forth
    public void prepGUI(){
        getContentPane().removeAll();
        setTitle("Mastermind - Color Squares Edition");
        
        //instructions at the top
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(new JLabel("Available colors: " + String.join(", ", game.level.getAvailableColors())));
        topPanel.add(new JLabel("Click one color per slot to make your guess"));
        add(topPanel, BorderLayout.NORTH);
        guessPanel = new JPanel();
        guessPanel.setLayout(new GridLayout(game.getLength(), 1));
        add(guessPanel, BorderLayout.CENTER);
        buildColorSelectors();
        
        //
        submitButton = new JButton("Submit Guess");
        submitButton.addActionListener(e -> {
            if (currentState instanceof PlayingState){
                PlayingState state = (PlayingState) currentState;
                state.handleGuess(this);
            }});
        add(submitButton, BorderLayout.SOUTH);

        feedbackArea = new JTextArea(6, 60);
        feedbackArea.setEditable(false);
        add(new JScrollPane(feedbackArea), BorderLayout.EAST);

        revalidate();
        repaint();
    }

    private void buildColorSelectors() {
        String[] colors = game.level.getAvailableColors();
        //create as meany rows for colors in password
        for (int i = 0; i < game.getLength(); i++) {
            JPanel row = new JPanel();
            row.setBorder(BorderFactory.createTitledBorder("Slot " + (i + 1)));
            //add as many colors available to be chosen from
            for (int j = 0; j < colors.length; j++) {
                String colorName = colors[j];
                Color color = getColorFromName(colorName);
                JPanel colorCircle = new JPanel();
                colorCircle.setBackground(color);
                colorCircle.setPreferredSize(new Dimension(40, 40));
                colorCircle.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

                int slot = i;
                //highlight and add colors into selected
                colorCircle.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        //test for the weird selection lag - can't figure out
                        // System.out.println("Clicked slot " + slot + " color: " + colorName);

                        currentSelections.put(slot, colorName);
                        highlightSelectedColor(row, colorCircle);
                    }
                });

                row.add(colorCircle);
            }
            guessPanel.add(row);
        }
    }

    //when color selected, highlight the color 
    private void highlightSelectedColor(JPanel row, JPanel selectedCircle) {
        Component[] components = row.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JPanel) {
                ((JPanel) components[i]).setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
            }
        }
        selectedCircle.setBorder(BorderFactory.createLineBorder(SELECTED_BORDER_COLOR, 3));
    }

    //return the selection of colors and in which position they're in
    public Map<Integer, String> getCurrentSelections(){
        return currentSelections;
    }
    
    //clear current selection and refresh ui
    public void clearSelection(){

        currentSelections.clear();
        guessPanel.removeAll();
        buildColorSelectors();
        guessPanel.revalidate();
        guessPanel.repaint();
    }
    
    @Override
    public void update(int blackPegs, int whitePegs){
        currentChances--;
        //update feedback area with results of guess and how many chances are left
        feedbackArea.append(String.format(
            "You have %d guess(es) left.\nGuess: %s → %d correct color(s), %d in correct position.\n\n",
            currentChances, String.join(" ", guess), blackPegs + whitePegs, blackPegs));
        
        //check if game is over
        if (blackPegs == guess.length && whitePegs == 0) {
            setState(new EndingState(true));
        } else if (currentChances == 0) {
            setState(new EndingState(false));
        }
    }

    private Color getColorFromName(String name) {
        return switch (name.toUpperCase()) {
            case "RED" -> Color.RED;
            case "BLUE" -> Color.BLUE;
            case "GREEN" -> Color.GREEN;
            case "YELLOW" -> Color.YELLOW;
            case "PURPLE" -> new Color(128, 0, 128);
            case "ORANGE" -> Color.ORANGE;
            case "PINK" -> Color.PINK;
            case "BROWN" -> new Color(139, 69, 19);
            case "BLACK" -> Color.BLACK;
            case "WHITE" -> Color.WHITE;
            default -> Color.GRAY;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}
