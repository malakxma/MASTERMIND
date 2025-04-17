public class EasyLevel implements GameLevel {


    private static final String[] COLORS = {"Red", "Blue", "Green", "Yellow", "Purple", "Orange"};

    /**
     * Returns the length of the password for easy level.
     *
     * @return 4, indicating a 4-color password.
     */
    @Override
    public int getPasswordLength() {
        return 4;
    }

    /**
     * Returns the array of available colors for easy level.
     *
     * @return a String array containing 6 colors.
     */
    @Override
    public String[] getAvailableColors() {
        return COLORS;
    }
}
