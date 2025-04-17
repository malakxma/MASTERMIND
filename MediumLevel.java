public class MediumLevel implements GameLevel {


    private static final String[] COLORS = {"Red", "Blue", "Green", "Yellow", "Purple", "Orange", "Pink", "Brown"};

    /**
     * Returns the length of the password for medium level.
     *
     * @return 6, indicating a 6-color password.
     */
    @Override
    public int getPasswordLength() {
        return 6;
    }

    /**
     * Returns the array of available colors for medium level.
     *
     * @return a String array containing 8 colors.
     */
    @Override
    public String[] getAvailableColors() {
        return COLORS;
    }
}
