package de.ixsen.minecraft.uilib.elements.basic;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.elements.core.UIScalableElement;
import de.ixsen.minecraft.uilib.elements.core.data.UIAnimatedImageData;
import de.ixsen.minecraft.uilib.elements.core.data.UIImageData;

/**
 * @author Subaro
 */
public class UIAnimatedImage extends UIScalableElement {

    protected UIAnimatedImageData imageData;
    protected int tickSpeed;

    public UIAnimatedImage(UIAnimatedImageData imageData, int tickSpeed) {
        super(1);
        this.imageData = imageData;
        this.tickSpeed = tickSpeed;
    }

    public UIAnimatedImage(UIContainer parentContainer, UIAnimatedImageData imageData, int tickSpeed) {
        super(parentContainer, 1);
        this.imageData = imageData;
        this.tickSpeed = tickSpeed;
    }

    /*
     * (non-Javadoc)
     *
     * @see masteryUI.elements.core.UIElement#draw(int, int, int, int, float)
     */
    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        {
            this.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);

            // Draw Image if set
            if (this.imageData != null) {
                UIImageData currentImage = this.imageData.getNextImage(this.tickSpeed);
                Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
                ReadableDimension size = this.getMinimumSize();
                this.drawImageBind(currentImage, myGlobalPos.getX(), myGlobalPos.getY(), size.getWidth(),
                        size.getHeight());
            }
        }
        this.endScaling();
    }
}
