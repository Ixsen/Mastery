package masteryUI.layout;

import java.util.HashMap;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import masteryUI.elements.core.UIElement;

/**
 * Vertical layout.
 *
 * @author Subaro
 */
public class FreeFormLayout implements UILayout {

    @Override
    public void layoutElements(HashMap<UIElement, LayoutData> elementData) {
    }

    @Override
    public ReadableDimension calculateMinimumSize(HashMap<UIElement, LayoutData> elementData) {
        int maxHeight = 0;
        int maxWidth = 0;

        for (UIElement element : elementData.keySet()) {
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
