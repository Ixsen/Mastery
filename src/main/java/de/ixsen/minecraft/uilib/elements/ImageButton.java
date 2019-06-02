package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.data.ImageData;

/**
 * Represents a button that draws a given image into the button.
 *
 * @author Subaro
 */
public class ImageButton extends Button {

    /** The image that should be drawn into the button */
    private ImageData imageData;

    public ImageButton(ImageData imageData) {
        super("");
        this.imageData = imageData;
    }

    public ImageButton(UiContainer parentContainer, ImageData imageData) {
        super(parentContainer, "");
        this.imageData = imageData;
    }

    @Override
    public void drawBackground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        // Draw Image if set
        if (this.imageData != null) {
            ReadableDimension size = this.getMinimumSize();
            int drawSize = Math.min(size.getWidth() - 8, size.getHeight() - 8);
            int imageX = getGlobalPosition().getX() + size.getWidth() / 2 - drawSize / 2;
            int imageY = getGlobalPosition().getY() + size.getHeight() / 2 - drawSize / 2;
            this.drawImageBind(this.imageData, imageX, imageY, drawSize, drawSize);
        }
    }

}
