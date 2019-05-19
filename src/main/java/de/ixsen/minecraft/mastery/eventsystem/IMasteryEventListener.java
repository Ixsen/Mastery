package de.ixsen.minecraft.mastery.eventsystem;

/**
 * @author Subaro
 */
@FunctionalInterface
public interface IMasteryEventListener {

    void performEvent(MasteryEvent event);
}
