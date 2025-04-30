public interface GameLevel {
    //returns number of colors in password
    int getPasswordLength();

    //returns array of available colors to pick from
    String[] getAvailableColors();
    //returns number of possible chances
    int getMaxChances();

    // int getMaxHintAmount();
    //generates password
    String[] passwordGenerator();

}

