//Defines the “subject” that notifies observers whenever a guess happen

import java.util.*;

public interface FeedbackSubject {
    /**
     * Register an observer so it will receive feedback updates.
     * @param obs a FeedbackObserver to add
     */
    void registerObserver(FeedbackObserver obs);

    /**
     * Remove a previously registered observer.
     * @param obs the FeedbackObserver to remove
     */
    void removeObserver(FeedbackObserver obs);

    /**
     * Notify all registered observers of a new guess.
     * @param guess the player’s most recent color guess array
     */
    void notifyObservers(String[] guess);
}