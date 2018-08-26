package masteryUI.event;

/**
 * @author Subaro
 */
@FunctionalInterface
public interface UIEventRunnable<T extends UIEvent> {
    void run(T e);
}