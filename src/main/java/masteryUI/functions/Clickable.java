package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIMouseEvent;

/**
 * @author Subaro
 */
public interface Clickable {

    void addClickListener(Consumer<UIMouseEvent> onClick);

    /**
     * Is called when the element clicked.
     *
     * @return true, if the element should be omitted.
     */
    boolean onClick(int mouseX, int mouseY, int mouseButton);

    /**
     * Adds a listener to the element. The listener is called onRelease.
     *
     * @param onRelease Consumer to add
     */
    void addReleaseListener(Consumer<UIMouseEvent> onRelease);

    /**
     * Is called when the mouse button is released after clicking an element.
     *
     * @return true, if the element should be omitted.
     */
    boolean onRelease(int mouseX, int mouseY, int state);

    /**
     * Is used to determine if the current element is clicked.
     *
     * @return
     */
    boolean isClicked();
}
