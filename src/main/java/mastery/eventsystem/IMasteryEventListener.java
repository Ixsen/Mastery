package mastery.eventsystem;

/**
 * @author Subaro
 */
@FunctionalInterface
public interface IMasteryEventListener {

    void performEvent(MasteryEvent event);
}
