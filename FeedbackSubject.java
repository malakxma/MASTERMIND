//Defines the “subject” that notifies observers whenever a guess happen

import java.util.*;

public interface FeedbackSubject {
    //regeister observer
    void registerObserver(FeedbackObserver obs);

   //remove observer
    void removeObserver(FeedbackObserver obs);

    //notify observers of new guess
    void notifyObservers(String[] guess);
}