/**
 * 
 */
package mastery.eventsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Subaro
 */
public class MasteryEventHandler {

    protected List<IMasteryEventListener> listeners = new ArrayList<>();

    public void addListener(IMasteryEventListener listener) {
	if (!listeners.contains(listener)) {
	    listeners.add(listener);
	}
    }

    public void removeListener(IMasteryEventListener listener) {
	if (listeners.contains(listener)) {
	    listeners.remove(listener);
	}
    }

    public void fireEvent(MasteryEvent event) {
	for (IMasteryEventListener iMasteryEventListener : listeners) {
	    iMasteryEventListener.performEvent(event);
	}
    }
}
