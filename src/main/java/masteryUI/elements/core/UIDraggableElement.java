package masteryUI.elements.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.Point;

import masteryUI.event.UIMouseEvent;
import masteryUI.functions.Draggable;

/**
 * @author Subaro
 */
public class UIDraggableElement extends UIClickableElement implements Draggable {

    /** List containing the consumers for the on drag event */
    private List<Consumer<UIMouseEvent>> onDragListener = new ArrayList<>();

    public UIDraggableElement(float scale) {
        super(scale);
    }

    public UIDraggableElement(UIContainer parentContainer, float scale) {
        super(parentContainer, scale);
    }

    @Override
    public void addDragListener(Consumer<UIMouseEvent> onDrag) {
        this.onDragListener.add(onDrag);
    }

    @Override
    public boolean onDrag(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        for (Consumer<UIMouseEvent> consumer : this.onDragListener) {
            consumer.accept(new UIMouseEvent(this, new Point(mouseX, mouseY), clickedMouseButton, timeSinceLastClick));
        }
        return true;
    }
}
