package de.ixsen.minecraft.uilib.layout;

import java.util.HashMap;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.UiElement;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

/**
 * Vertical layout.
 *
 * @author Subaro
 */
public class VerticalLayout implements UiLayout {

    private int paddingLeft = 0;
    private int paddingRight = 0;
    private int paddingTop = 0;
    private int paddingBot = 0;
    private int spacing = 0;

    public VerticalLayout() {

    }

    public VerticalLayout(int paddingLeft, int paddingRight, int paddingTop, int paddingBot, int spacing) {
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingTop = paddingTop;
        this.paddingBot = paddingBot;
        this.spacing = spacing;
    }

    @Override
    public void layoutElements(HashMap<UiElement, LayoutData> elementData) {
        int currentX = this.paddingLeft;
        int currentY = this.paddingTop;
        for (UiElement element : elementData.keySet()) {
            if (element instanceof UiContainer) {
                ((UiContainer) element).layoutElements();
            }

            VerticalData data = null;
            if (elementData.get(element) instanceof VerticalData) {
                data = (VerticalData) elementData.get(element);
            } else {
                data = VerticalData.DEFAULT;
            }
            currentY += data.getPaddingTop();
            element.setRelativePosition(new Point(currentX, currentY));
            currentY += data.getPaddingBot();

            // Shift the elements to the bottom
            currentY += this.spacing + element.getMinimumSize().getHeight();
        }
    }

    @Override
    public ReadableDimension calculateMinimumSize(HashMap<UiElement, LayoutData> elementData) {
        // Add top padding, bottom padding and spacing for n-1 elements ;)
        int minHeight = this.paddingTop + this.paddingBot + elementData.keySet().size() > 0
                ? (elementData.keySet().size() - 1) * this.spacing
                : 0;
        int minWidth = 0;

        for (UiElement element : elementData.keySet()) {
            ReadableDimension elementSize = element.getMinimumSize();
            minHeight += elementSize.getHeight();
            if (elementSize.getWidth() > minWidth) {
                minWidth = elementSize.getWidth();
            }
        }

        minWidth += this.paddingLeft + this.paddingRight;
        return new Dimension(minHeight, minWidth);
    }

    @Override
    public LayoutData getDefaultData() {
        return VerticalData.DEFAULT;
    }
}
