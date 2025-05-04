import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class GameGUI extends JFrame implements FeedbackObserver{
    private GameController controller;
    private Game game;
    private String[] guess;
    private int currentChances;

    private JTextArea feedbackArea;
    private JButton submitButton;

    private JPanel guessPanel;
    private Map<Integer, String> currentSelections = new HashMap<>();
    private final Color SELECTED_BORDER_COLOR = Color.BLACK;

    public GameGUI() {
        controller = new GameController();
        setupDifficultyMenu();
    }


    private void setupDifficultyMenu() {
        //details on the level differences
        String[] options = {"Easy (4)", "Medium (6)", "Hard (8)"};
        int difficulty = JOptionPane.showOptionDialog(this,
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
        game = new Game(level);
        game.registerObserver(this);
        controller.setGame(game);
        currentChances = game.getMaxChances();
        guess = new String[game.getLength()];

        setupGamePanel();
    }

    private void setupGamePanel() {
        setTitle("Mastermind - Color Squares Edition");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); //make game full screen on start
        setLayout(new BorderLayout());

        //instructions at the top
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(new JLabel("Available colors: " + String.join(", ", game.level.getAvailableColors())));
        topPanel.add(new JLabel("Click one color per slot to make your guess"));
        add(topPanel, BorderLayout.NORTH);

        //create area for color selection
        guessPanel = new JPanel();
        guessPanel.setLayout(new GridLayout(game.getLength(), 1));
        add(guessPanel, BorderLayout.CENTER);

        buildColorSelectors();

        //create feedback area
        submitButton = new JButton("Submit Guess");
        submitButton.addActionListener(e -> handleGuess());
        add(submitButton, BorderLayout.SOUTH);

        feedbackArea = new JTextArea(6, 60);
        feedbackArea.setEditable(false);
        add(new JScrollPane(feedbackArea), BorderLayout.EAST);

        setVisible(true);
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
                        //test for the weird selection lag
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

    private void handleGuess() {
        //handle if not enough colors selected
        if (currentSelections.size() != game.getLength()) {
            JOptionPane.showMessageDialog(this, "Please make a selection for each slot.", "Incomplete Guess", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (int i = 0; i < game.getLength(); i++) {
            guess[i] = currentSelections.get(i);
        }

        try {
            //notify observers of guess
            game.notifyObservers(guess);

            //clear current selection and refresh ui
            currentSelections.clear();
            guessPanel.removeAll();
            buildColorSelectors();
            guessPanel.revalidate();
            guessPanel.repaint();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Color", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void update(int blackPegs, int whitePegs){
        currentChances--;
        //update feedback area with results of guess and how many chances are left
        feedbackArea.append(String.format(
            "You have %d guess(es) left.\nGuess: %s → %d correct color(s), %d in correct position.\n",
            currentChances, String.join(" ", guess), blackPegs + whitePegs, blackPegs));
        
        //check if game is over
        if (blackPegs == guess.length && whitePegs == 0) {
            showEndDialog(true);
        } else if (currentChances == 0) {
            showEndDialog(false);
        }
    }
    private void showEndDialog(boolean win) {
        String message = win ? "🎉 You guessed the password!" : "💀 Game over!";
        message += "\nThe correct password was: " + String.join(" ", game.getSecretPassword());

        int choice = JOptionPane.showConfirmDialog(this, message + "\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new GameGUI();
        } else {
            System.exit(0);
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
