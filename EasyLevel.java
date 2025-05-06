import java.util.*;
public class EasyLevel implements GameLevel {


    private static final String[] COLORS = {"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE"};

    @Override
    public int getPasswordLength() {
        return 4;
    }

    @Override
    public String[] getAvailableColors() {
        return COLORS;
    }
    @Override
    public int getMaxChances(){
        return 10;
    }
   
    @Override
    public String[] passwordGenerator(){ //generate password that doesn't have any repeat colors
        String[] password = new String[getPasswordLength()];
        Set<String> used = new HashSet<>();
        String color;
        Random rand = new Random();
        for (int i=0; i<getPasswordLength(); i++){
            do {
                color = COLORS[rand.nextInt(COLORS.length)];
            } while (used.contains(color)); // Keep generating until get a unique color
            password[i] = color;
            used.add(color);  // Mark color as used
        
        }
        return password;
    }
}
