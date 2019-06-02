package de.ixsen.minecraft.mastery.eventsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Subaro
 */
public class MasteryEventHandler {

    private List<IMasteryEventListener> listeners = new ArrayList<>();

    public void addListener(IMasteryEventListener listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    public void removeListener(IMasteryEventListener listener) {
        this.listeners.remove(listener);
    }

    public void fireEvent(MasteryEvent event) {
        this.listeners.forEach(IMasteryEventListener -> IMasteryEventListener.performEvent(event));
    }
}
