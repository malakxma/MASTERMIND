import java.util.*;
public class MediumLevel implements GameLevel {


    private static final String[] COLORS = {"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE", "PINK", "BROWN"};

    @Override
    public int getPasswordLength() {
        return 6;
    }


    @Override
    public String[] getAvailableColors() {
        return COLORS;
    }
    @Override
    public int getMaxChances(){
        return 12;
    }
    // @Override
    // public int getMaxHintAmount(){
    //     return 12;
    // }
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
