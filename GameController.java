import java.util.*;

public class GameController {
    GameState state;
    Game newGame;
    Scanner scanner = new Scanner(System.in);
    public void setState(GameState newState){
        this.state=newState;
    };

    public void start(){
        setState(new ChooseLevel(this, scanner));
        while (state!=null) {
            state.run();
        }
    }

    public void setGame(Game Gameplay){
        this.newGame = Gameplay;
    }
    public Game getGame(){
        return newGame;
    }
}
