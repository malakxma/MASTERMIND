import java.util.*;
public class HardLevel implements GameLevel {


    private static final String[] COLORS = {"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE", "PINK", "BROWN", "BLACK", "WHITE"};

    //returns lenght of passwor for hard - 8
    public int getPasswordLength() {
        return 8;
    }

    //returns array of avaiable colors - 10 
    @Override
    public String[] getAvailableColors() {
        return COLORS;
    }

    //returns int of max chances -14
    @Override
    public int getMaxChances(){
        return 14;
    }
   

    //generates a new secret password repeats allowed
    @Override
    public String[] passwordGenerator(){
        String[] password = new String[getPasswordLength()];
        String color;
        Random rand = new Random();
        for (int i=0; i<getPasswordLength(); i++){
            color = COLORS[rand.nextInt(COLORS.length)];
            password[i]=color;
        
        }
        return password;
    }
}
