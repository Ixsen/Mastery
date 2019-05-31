package de.ixsen.minecraft.uilib.elements.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import org.lwjgl.util.Point;

import de.ixsen.minecraft.uilib.event.UIMouseEvent;
import de.ixsen.minecraft.uilib.functions.Draggable;

/**
 * @author Subaro
 */
public abstract class DraggableGuiElement extends ClickableGuiElement implements Draggable {

    /** Indicates that the element was clicked. Is used to determine if a drag event is associated to the DraggableGuiElement */
    private boolean isClicked;
    /** List containing the consumers for the on release event */
    private List<Consumer<UIMouseEvent>> onReleaseListener = new ArrayList<>();
    /** List containing the consumers for the on drag event */
    private List<Consumer<UIMouseEvent>> onDragListener = new ArrayList<>();

    public DraggableGuiElement(float scale) {
        super(scale);
    }

    public DraggableGuiElement(GuiContainer parentContainer, float scale) {
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

    @Override
    public void addReleaseListener(Consumer<UIMouseEvent> onRelease) {
        this.onReleaseListener.add(onRelease);
    }

    @Override
    public boolean onRelease(int mouseX, int mouseY, int state) {
        this.isClicked = false;
        for (Consumer<UIMouseEvent> consumer : this.onReleaseListener) {
            consumer.accept(new UIMouseEvent(this, new Point(mouseX, mouseY), 0, state));
        }
        return true;
    }

    @Override
    public boolean isClicked() {
        return this.isClicked;
    }
}
