package de.ixsen.minecraft.uilib.elements;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import de.ixsen.minecraft.uilib.elements.core.ScalableGuiElement;
import de.ixsen.minecraft.uilib.elements.core.data.ImageData;

/**
 * @author Subaro
 */
public class Image extends ScalableGuiElement {

    /** The image that should be drawn into the button */
    protected ImageData imageData;

    public Image(ImageData imageData) {
        super(1);
        this.imageData = imageData;
    }

    public Image(GuiContainer parentContainer, ImageData imageData) {
        super(parentContainer, 1);
        this.imageData = imageData;
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        // TODOs
    }
}
