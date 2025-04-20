public interface GameLevel {
    /**
     * Returns the length of the secret password.
     *
     * @return the number of colors in the password.
     */
    int getPasswordLength();

    /**
     * Returns the array of available colors that the player can choose from.
     *
     * @return an array of color names.
     */
    String[] getAvailableColors();

    int getMaxChances();

    // int getMaxHintAmount();

    String[] passwordGenerator();

}

