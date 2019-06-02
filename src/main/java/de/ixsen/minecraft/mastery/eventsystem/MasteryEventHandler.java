package de.ixsen.minecraft.mastery.eventsystem;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Subaro
 */
public class MasteryEventHandler {

    private EventContainer listeners = new EventContainer();

    public <EVENT_TYPE> void addListener(Consumer<EVENT_TYPE> listener, Class<EVENT_TYPE> type) {
        this.listeners.put(listener, type);
    }

    public <EVENT_TYPE> void removeListener(Consumer<EVENT_TYPE> listener, Class<EVENT_TYPE> type) {
        this.listeners.remove(listener, type);
    }

    @SuppressWarnings("unchecked")
    public <EVENT_TYPE> void fireEvent(EVENT_TYPE event) {
        List<Consumer> listenersForEventType = this.listeners.getListenersForEventType(event.getClass());

        listenersForEventType.forEach(consumer -> consumer.accept(event));
    }
}
