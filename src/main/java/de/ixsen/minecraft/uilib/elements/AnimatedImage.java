package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.ScalableUiElement;
import de.ixsen.minecraft.uilib.elements.core.data.AnimatedImageData;
import de.ixsen.minecraft.uilib.elements.core.data.ImageData;

/**
 * @author Subaro
 */
public class AnimatedImage extends ScalableUiElement {

    protected AnimatedImageData imageData;
    protected int tickSpeed;

    public AnimatedImage(AnimatedImageData imageData, int tickSpeed) {
        super(1);
        this.imageData = imageData;
        this.tickSpeed = tickSpeed;
    }

    public AnimatedImage(UiContainer parentContainer, AnimatedImageData imageData, int tickSpeed) {
        super(parentContainer, 1);
        this.imageData = imageData;
        this.tickSpeed = tickSpeed;
    }

    /*
     * (non-Javadoc)
     *
     * @see masteryUI.elements.core.UiElement#draw(int, int, int, int, float)
     */
    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.imageData == null) {
            return;
        }

        ImageData currentImage = this.imageData.getNextImage(this.tickSpeed);
        ReadableDimension size = this.getMinimumSize();
        this.drawImageBind(currentImage, this.getGlobalPosition().getX(), this.getGlobalPosition().getY(), size.getWidth(),
                size.getHeight());
    }
}
