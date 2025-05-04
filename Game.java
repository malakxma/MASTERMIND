import java.util.*;
public class Game implements FeedbackSubject{

    GameLevel level;
    int maxChance;
    int passLength;
    String[] possibleColors;
    String[] secretPassword;
    public List<FeedbackObserver> observers = new ArrayList<>();

    public Game(GameLevel strategy){
        this.level = strategy;
        this.maxChance = strategy.getMaxChances();
        this.possibleColors = strategy.getAvailableColors();
        this.secretPassword = strategy.passwordGenerator();
        this.passLength = strategy.getPasswordLength();

    }

    //register observer
    @Override
    public void registerObserver(FeedbackObserver obs){
        observers.add(obs);
    };

   //remove observer
   @Override
    public void removeObserver(FeedbackObserver obs){
        observers.remove(obs);
    };

    //notify observers of new guess
    @Override
    public void notifyObservers(String[] guess){
        for (String color : guess){
            validateColor(color);
        }
        int black = correctColorPos(guess); //num colors that are correct color and position
        int white = correctColor(guess)-black; //num colors only in correct position, subtract black because we only want correct color
        for (FeedbackObserver obs: observers){
            obs.update(black, white);
        }

    };
    
    //getter for how many chances
    public int getMaxChances(){
        return maxChance;
    }
    //getter for how long password is
    public int getLength(){
        return passLength;
    }
    //getter for correct password
    public String[] getSecretPassword(){
        return secretPassword;
    }
    //check if all colors are allowed for password
    public String validateColor(String color){
        color = color.toUpperCase();
        if (Arrays.asList(possibleColors).contains(color)){
            return color;
        }
        else {
            throw new IllegalArgumentException(color+ " is not a valid input for password");
        }
    }
    //feedback for textbased game - check if there are correct colors present
    public int correctColor(String[] colors){
        int correct = 0;
        for (String colorChoice: colors){
            if (Arrays.asList(secretPassword).contains(colorChoice)){
                correct+=1;
            }
        }
        return correct;
    }
    //check if there are correct colors in the correct position
    public int correctColorPos(String[] colors){
        int correct = 0;
        for (int i=0; i<passLength; i++){
            if (secretPassword[i].equals(colors[i])){
                correct+=1;
            }
        }
        return correct;
    }
}
