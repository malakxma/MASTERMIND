import java.util.*;
public class Game {

    GameLevel level;
    int maxChance;
    int passLength;
    String[] possibleColors;
    String[] secretPassword;

    public Game(GameLevel strategy){
        this.level = strategy;
        this.maxChance = strategy.getMaxChances();
        this.possibleColors = strategy.getAvailableColors();
        this.secretPassword = strategy.passwordGenerator();
        this.passLength = strategy.getPasswordLength();

    }

    public int getMaxChances(){
        return maxChance;
    }

    public int getLength(){
        return passLength;
    }
    public String[] getSecretPassword(){
        return secretPassword;
    }

    public String validateColor(String color){
        color = color.toUpperCase();
        if (Arrays.asList(possibleColors).contains(color)){
            return color;
        }
        else {
            throw new IllegalArgumentException(color+ " is not a valid input for password");
        }
    }

    public int correctColor(String[] colors){
        int correct = 0;
        for (String colorChoice: colors){
            if (Arrays.asList(secretPassword).contains(colorChoice)){
                correct+=1;
            }
        }
        return correct;
    }

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
