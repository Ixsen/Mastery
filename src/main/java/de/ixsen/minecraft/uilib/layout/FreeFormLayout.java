package de.ixsen.minecraft.uilib.layout;

import java.util.HashMap;

import de.ixsen.minecraft.uilib.elements.core.GuiElement;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

/**
 * Vertical layout.
 *
 * @author Subaro
 */
public class FreeFormLayout implements GuiLayout {

    @Override
    public void layoutElements(HashMap<GuiElement, LayoutData> elementData) {
    }

    @Override
    public ReadableDimension calculateMinimumSize(HashMap<GuiElement, LayoutData> elementData) {
        int maxHeight = 0;
        int maxWidth = 0;

        for (GuiElement element : elementData.keySet()) {
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
