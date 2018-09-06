package masteryUI.elements.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.Point;

import masteryUI.event.UIMouseEvent;
import masteryUI.functions.Clickable;

/**
 * @author Subaro
 */
public abstract class UIClickableElement extends UIScalableElement implements Clickable {

    /** Indicates that the element was clicked. Is used to determine if a drag event is associated to the UIDraggableElement */
    private boolean isClicked;
    /** List containing the consumers for the on click event */
    private List<Consumer<UIMouseEvent>> onClickListener = new ArrayList<>();
    /** List containing the consumers for the on release event */
    private List<Consumer<UIMouseEvent>> onReleaseListener = new ArrayList<>();

    public UIClickableElement(float scale) {
        super(scale);
    }

    public UIClickableElement(UIContainer parentContainer, float scale) {
        super(parentContainer, scale);
    }

    @Override
    public void addClickListener(Consumer<UIMouseEvent> onClick) {
        this.onClickListener.add(onClick);
    }

    @Override
    public boolean onClick(int mouseX, int mouseY, int mouseButton) {
        this.isClicked = true;
        for (Consumer<UIMouseEvent> consumer : this.onClickListener) {
            consumer.accept(new UIMouseEvent(this, new Point(mouseX, mouseY), mouseButton));
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
