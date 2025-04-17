public class HardLevel implements GameLevel {


    private static final String[] COLORS = {"Red", "Blue", "Green", "Yellow", "Purple", "Orange", "Pink", "Brown", "Black", "White"};

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
}
