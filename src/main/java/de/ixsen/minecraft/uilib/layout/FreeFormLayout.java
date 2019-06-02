package de.ixsen.minecraft.uilib.layout;

import java.util.HashMap;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.core.UiElement;

/**
 * Vertical layout.
 *
 * @author Subaro
 */
public class FreeFormLayout implements UiLayout {

    @Override
    public void layoutElements(HashMap<UiElement, LayoutData> elementData) {
    }

    @Override
    public ReadableDimension calculateMinimumSize(HashMap<UiElement, LayoutData> elementData) {
        int maxHeight = 0;
        int maxWidth = 0;

        for (UiElement element : elementData.keySet()) {
            ReadableDimension elementSize = element.getMinimumSize();
            Point pos = element.getGlobalPosition();
            if (pos.getX() + elementSize.getWidth() > maxWidth) {
                maxWidth = pos.getX() + elementSize.getWidth();
            }
            if (pos.getY() + elementSize.getHeight() > maxHeight) {
                maxHeight = pos.getY() + elementSize.getHeight();
            }
        }
        return new Dimension(maxWidth, maxHeight);
    }

    @Override
    public LayoutData getDefaultData() {
        return FreeFormData.DEFAULT;
    }
}
