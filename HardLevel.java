import java.util.*;
public class HardLevel implements GameLevel {


    private static final String[] COLORS = {"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE", "PINK", "BROWN", "BLACK", "WHITE"};

    /**
     * Returns the length of the password for hard level.
     *
     * @return 8, indicating an 8-color password.
     */
    @Override
    public int getPasswordLength() {
        return 8;
    }

    /**
     * Returns the array of available colors for hard level.
     *
     * @return a String array containing 10 colors.
     */
    @Override
    public String[] getAvailableColors() {
        return COLORS;
    }
    @Override
    public int getMaxChances(){
        return 14;
    }
    // @Override
    // public int getMaxHintAmount(){
    //     return 14;
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
