//for ANy class that wants to react to feedback implements this.

public interface FeedbackObserver {
    /**
     * Called when FeedbackSubject.notifyObservers(...) is invoked.
     * @param blackPegs  exact matches (right color, right position)
     * @param whitePegs  partial matches (right color, wrong position)
     */
    void update(int blackPegs, int whitePegs);
}
