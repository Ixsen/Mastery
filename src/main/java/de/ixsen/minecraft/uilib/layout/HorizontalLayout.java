package de.ixsen.minecraft.uilib.layout;

import java.util.HashMap;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import de.ixsen.minecraft.uilib.elements.core.GuiElement;

/**
 * Default layout. Every element is placed side by side.
 *
 * @author Subaro
 */
public class HorizontalLayout implements GuiLayout {

    private int paddingLeft = 0;
    private int paddingRight = 0;
    private int paddingTop = 0;
    private int paddingBot = 0;
    private int spacing = 0;

    public HorizontalLayout() {

    }

    public HorizontalLayout(int paddingLeft, int paddingRight, int paddingTop, int paddingBot, int spacing) {
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingTop = paddingTop;
        this.paddingBot = paddingBot;
        this.spacing = spacing;
    }

    @Override
    public void layoutElements(HashMap<GuiElement, LayoutData> elements) {
        int currentX = this.paddingLeft;
        int currentY = this.paddingTop;
        for (GuiElement element : elements.keySet()) {
            if (element instanceof GuiContainer) {
                ((GuiContainer) element).layoutElements();
            }

            HorizontalData data = null;
            if (elements.get(element) instanceof HorizontalData) {
                data = (HorizontalData) elements.get(element);
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

    @Override
    public ReadableDimension calculateMinimumSize(HashMap<GuiElement, LayoutData> elementData) {
        // Add left padding, right padding and spacing for n-1 elements ;)
        int minWidth = this.paddingLeft + this.paddingRight + elementData.keySet().size() > 0
                ? (elementData.keySet().size() - 1) * this.spacing
                : 0;
        int maxHeight = 0;

        for (GuiElement element : elementData.keySet()) {
            ReadableDimension elementSize = element.getMinimumSize();
            minWidth += elementSize.getWidth();
            if (elementSize.getHeight() > maxHeight) {
                maxHeight = elementSize.getHeight();
            }
        }

        maxHeight += this.paddingBot + this.paddingTop;
        return new Dimension(minWidth, maxHeight);
    }

    @Override
    public LayoutData getDefaultData() {
        return HorizontalData.DEFAULT;
    }
}
