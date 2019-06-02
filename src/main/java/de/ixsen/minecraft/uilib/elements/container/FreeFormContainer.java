package de.ixsen.minecraft.uilib.elements.container;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.core.ScalableUiElement;
import de.ixsen.minecraft.uilib.elements.core.UiElement;
import de.ixsen.minecraft.uilib.elements.core.UiScreen;

public class FreeFormContainer extends UiContainer {

    public FreeFormContainer() {
    }

    public FreeFormContainer(UiScreen screen) {
        super(screen);
    }

    @Override
    public void layoutElements() {
        int maxHeight = 0;
        int maxWidth = 0;

        for (UiElement element : this.containedElements) {
            if (element instanceof UiContainer) {
                ((UiContainer) element).layoutElements();
            }

            float scaleCounter = element instanceof ScalableUiElement
                    ? (int) (1 / ((ScalableUiElement) element).getScale())
                    : 1;

            Point elementGlobalPosition = new Point(
                    (int) (this.getGlobalPosition().getX() * scaleCounter + element.getRelativePosition().getX()),
                    (int) (this.getGlobalPosition().getY() * scaleCounter + element.getRelativePosition().getY()));

            element.setGlobalPosition(elementGlobalPosition);

            ReadableDimension elementSize = element.getMinimumSize();
            if (elementGlobalPosition.getX() + elementSize.getWidth() > maxWidth) {
                maxWidth = elementGlobalPosition.getX() + elementSize.getWidth();
            }
            if (elementGlobalPosition.getY() + elementSize.getHeight() > maxHeight) {
                maxHeight = elementGlobalPosition.getY() + elementSize.getHeight();
            }
        }

        this.setSize(maxWidth, maxHeight);
    }
}
