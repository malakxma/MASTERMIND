public class GameLevelFactory {
    /**
     * Creates a GameLevel implementation based on the given difficulty.
     *
     * @param difficulty 1 for Easy, 2 for Medium, 3 for Hard
     * @return the corresponding GameLevel
     */
    public static GameLevel create(int difficulty) {
        switch (difficulty) {
            case 1:
                return new EasyLevel();
            case 2:
                return new MediumLevel();
            case 3:
                return new HardLevel();
            default:
                throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
    }
}
