
public class GameController {
    GameState state;
    public void setState(GameState newState){
        this.state=newState;
    };

    public void start(){
        setState(new ChooseLevel());
        while (state!=null) {
            state.run();
        }
    }

}
