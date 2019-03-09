package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIFocusEvent;

/**
 * @author Subaro
 */
public interface Focusable {

    /**
     * Adds a consumer to the element that is called when the focus of this element is changed.
     * 
     * @param onFocusChanged
     */
    void addFocusChangeListener(Consumer<UIFocusEvent> onFocusChanged);

    /**
     * Sets the current element to focus.
     * 
     * @param focus
     *            sets the focus value
     */
    void setFocused(boolean focus);

    /**
     * @return true, if the element is currently focused.
     */
    boolean isFocused();
}
