package de.ixsen.minecraft.mastery.eventsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

class EventContainer {

    private HashMap<Class, List<Consumer>> map;

    EventContainer() {
        this.map = new HashMap<>();
    }

    <T> void put(Consumer<T> value, Class type) {
        this.createSublistForType(type);
        this.map.get(type).add(value);

    }

    <T> void remove(Consumer<T> listener, Class type) {
        this.createSublistForType(type);
        this.map.get(type).remove(listener);

    }

    private void createSublistForType(Class type) {
        if (!this.map.containsKey(type)) {
            this.map.put(type, new ArrayList<>());
        }
    }

    List<Consumer> getListenersForEventType(Class type) {
        return this.map.get(type);
    }
}
