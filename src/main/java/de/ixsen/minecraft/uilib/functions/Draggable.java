package de.ixsen.minecraft.uilib.functions;

import java.util.function.Consumer;

import de.ixsen.minecraft.uilib.event.UIMouseEvent;

/**
 * @author Subaro
 */
public interface Draggable extends Clickable {
    /**
     * Adds a listener to the element. The listener is called onDrag.
     *
     * @param onDrag
     *            Consumer to add
     */
    void addDragListener(Consumer<UIMouseEvent> onDrag);

    /**
     * Is called when the mouse button is held down and moved. Requires that the element got the first click.
     *
     * @return true, if the element should be omitted.
     */
    boolean onDrag(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick);

    /**
     * Adds a listener to the element. The listener is called onRelease.
     *
     * @param onRelease
     *            Consumer to add
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
