package de.ixsen.minecraft.mastery.eventsystem;

/**
 * @author Subaro
 */
@FunctionalInterface
public interface MasteryEventListener {

    void performEvent(Object event);

}
