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

    /** List containing the consumers for the on click event */
    private List<Consumer<UIMouseEvent>> onClickListener = new ArrayList<>();

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
        for (Consumer<UIMouseEvent> consumer : this.onClickListener) {
            consumer.accept(new UIMouseEvent(this, new Point(mouseX, mouseY), mouseButton));
        }
        return true;
    }
}
