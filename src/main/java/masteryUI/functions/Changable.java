package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIValueChangeEvent;

/**
 * @author Subaro
 */
public interface Changable<T> {

    /**
     * Adds a new consumer to the element that is executed if the value changes.
     */
    void addChangeListener(Consumer<UIValueChangeEvent<T>> onValueChange);

    /**
     * Called when the value of hte element changed.
     */
    void onValueChanged();

}
