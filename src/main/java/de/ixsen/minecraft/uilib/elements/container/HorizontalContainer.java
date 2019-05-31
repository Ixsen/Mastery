package de.ixsen.minecraft.uilib.elements.container;

import org.lwjgl.util.Point;

import de.ixsen.minecraft.uilib.elements.core.GuiElement;
import de.ixsen.minecraft.uilib.layout.HorizontalData;

public class HorizontalContainer extends GuiContainer {

    private int paddingLeft = 0;
    private int paddingRight = 0;
    private int paddingTop = 0;
    private int paddingBot = 0;
    private int spacing = 0;

    public HorizontalContainer() {

    }

    public HorizontalContainer(int paddingLeft, int paddingRight, int paddingTop, int paddingBot, int spacing) {
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingTop = paddingTop;
        this.paddingBot = paddingBot;
        this.spacing = spacing;
    }

    @Override
    public void layoutElements() {
        int currentX = this.paddingLeft;
        int currentY = this.paddingTop;

        for (GuiElement element : this.containedElements.keySet()) {
            if (element instanceof GuiContainer) {
                ((GuiContainer) element).layoutElements();
            }

            HorizontalData data = null;
            if (this.containedElements.get(element) instanceof HorizontalData) {
                data = (HorizontalData) this.containedElements.get(element);
            } else {
                data = HorizontalData.DEFAULT;
            }
            currentX += data.getPaddingLeft();
            element.setRelativePosition(new Point(currentX, currentY));
            currentX += data.getPaddingRight();

            // Shift the elements to the right
            currentX += this.spacing + element.getMinimumSize().getWidth();
        }

    }
}
