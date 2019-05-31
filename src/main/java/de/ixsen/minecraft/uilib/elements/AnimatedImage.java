package de.ixsen.minecraft.uilib.elements;

import de.ixsen.minecraft.uilib.elements.core.ScalableGuiElement;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import de.ixsen.minecraft.uilib.elements.core.data.AnimatedImageData;
import de.ixsen.minecraft.uilib.elements.core.data.ImageData;

/**
 * @author Subaro
 */
public class AnimatedImage extends ScalableGuiElement {

    protected AnimatedImageData imageData;
    protected int tickSpeed;

    public AnimatedImage(AnimatedImageData imageData, int tickSpeed) {
        super(1);
        this.imageData = imageData;
        this.tickSpeed = tickSpeed;
    }

    public AnimatedImage(GuiContainer parentContainer, AnimatedImageData imageData, int tickSpeed) {
        super(parentContainer, 1);
        this.imageData = imageData;
        this.tickSpeed = tickSpeed;
    }

    /*
     * (non-Javadoc)
     *
     * @see masteryUI.elements.core.GuiElement#draw(int, int, int, int, float)
     */
    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.imageData == null) {
            return;
        }

        ImageData currentImage = this.imageData.getNextImage(this.tickSpeed);
        Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
        ReadableDimension size = this.getMinimumSize();
        this.drawImageBind(currentImage, myGlobalPos.getX(), myGlobalPos.getY(), size.getWidth(), size.getHeight());
    }
}
