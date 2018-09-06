package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIMouseEvent;

/**
 * @author Subaro
 */
public interface Draggable extends Clickable {
    /**
     * Adds a listener to the element. The listener is called onDrag.
     *
     * @param onDrag Consumer to add
     */
    void addDragListener(Consumer<UIMouseEvent> onDrag);

    /**
     * Is called when the mouse button is held down and moved. Requires that the element got the first click.
     *
     * @return true, if the element should be omitted.
     */
    boolean onDrag(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick);
}
