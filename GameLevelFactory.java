public class GameLevelFactory {
    //creates gamelevle implemenation based on difficulty
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
