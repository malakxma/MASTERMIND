// import java.util.*;

// public class GameController {
//     GameState state;
//     Game newGame;
//     Scanner scanner = new Scanner(System.in);

//     //setter for states
//     public void setState(GameState newState){
//         this.state=newState;
//     };
//     //starts the game loop
//     public void start(){
//         setState(new ChooseLevel(this, scanner));
//         while (state!=null) {
//             state.run();
//         }
//     }
//     //setter for a game
//     public void setGame(Game Gameplay){
//         this.newGame = Gameplay;
//     }

//     //getter for a game
//     public Game getGame(){
//         return newGame;
//     }
// }
