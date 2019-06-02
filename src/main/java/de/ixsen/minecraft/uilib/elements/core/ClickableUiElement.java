package de.ixsen.minecraft.uilib.elements.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.event.UIMouseEvent;
import de.ixsen.minecraft.uilib.functions.Clickable;
import org.lwjgl.util.Point;

/**
 * @author Subaro
 */
public abstract class ClickableUiElement extends ScalableUiElement implements Clickable {

    /** List containing the consumers for the on click event */
    private List<Consumer<UIMouseEvent>> onClickListener = new ArrayList<>();

    public ClickableUiElement(float scale) {
        super(scale);
    }

    public ClickableUiElement(UiContainer parentContainer, float scale) {
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
