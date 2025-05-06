//for ANy class that wants to react to feedback implements this.

public interface FeedbackObserver {

    //called when feedback subject invoked -> black = right color and right position, white = right color wrong position
    void update(int blackPegs, int whitePegs);
}
