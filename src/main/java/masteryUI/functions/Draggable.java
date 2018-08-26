package masteryUI.functions;

import org.lwjgl.util.Point;

import masteryUI.event.UIEventRunnable;

/**
 * @author Subaro
 */
public interface Draggable extends Clickable {
    void addDragListener(UIEventRunnable onDrag);

    void onDrag(Point mousePosition);

    void addReleaseListener(UIEventRunnable onRelease);

    void onRelease();
}
