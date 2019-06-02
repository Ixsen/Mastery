package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.data.AnimatedImageData;
import de.ixsen.minecraft.uilib.elements.core.data.ImageData;

/**
 * @author Subaro
 */
public class TiledAnimatedImage extends AnimatedImage {

    public TiledAnimatedImage(AnimatedImageData imageData, int tickSpeed) {
        super(imageData, tickSpeed);
    }

    public TiledAnimatedImage(UiContainer parentContainer, AnimatedImageData imageData, int tickSpeed) {
        super(parentContainer, imageData, tickSpeed);
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.imageData == null) {
            return;
        }

        ImageData currentImage = this.imageData.getNextImage(this.tickSpeed);
        ReadableDimension size = this.getMinimumSize();
        this.mc.renderEngine.bindTexture(this.imageData.getLocation());

        // Determine rows and columns
        for (int cY = 0; cY < size.getHeight(); cY += this.imageData.getvHeight()) {
            for (int cX = 0; cX < size.getWidth(); cX += this.imageData.getuWidth()) {
                int remainingWidth = size.getWidth() - cX;
                int remainingHeight = size.getHeight() - cY;
                if (remainingWidth >= this.imageData.getuWidth() && remainingHeight >= this.imageData.getvHeight()) {
                    // Draw a fully image
                    this.drawImage(currentImage, this.getGlobalPosition().getX() + cX,
                            this.getGlobalPosition().getY() + cY, this.imageData.getuWidth(),
                            this.imageData.getvHeight());
                } else if (remainingWidth < this.imageData.getuWidth()
                        && remainingHeight >= this.imageData.getvHeight()) {
                    float quotient = remainingWidth / (float) this.imageData.getuWidth();
                    this.drawSnippedImage(currentImage, this.getGlobalPosition().getX() + cX,
                            this.getGlobalPosition().getY() + cY, (int) (this.imageData.getuWidth() * quotient),
                            this.imageData.getvHeight(), quotient, 1);
                } else if (remainingWidth >= this.imageData.getuWidth()
                        && remainingHeight < this.imageData.getvHeight()) {
                    float quotient = remainingHeight / (float) this.imageData.getvHeight();
                    this.drawSnippedImage(currentImage, this.getGlobalPosition().getX() + cX,
                            this.getGlobalPosition().getY() + cY, this.imageData.getuWidth(),
                            (int) (this.imageData.getvHeight() * quotient), 1, quotient);
                } else {
                    float uQuotient = remainingWidth / (float) this.imageData.getuWidth();
                    float vQuotient = remainingHeight / (float) this.imageData.getvHeight();
                    this.drawSnippedImage(currentImage, this.getGlobalPosition().getX() + cX,
                            this.getGlobalPosition().getY() + cY, (int) (this.imageData.getuWidth() * uQuotient),
                            (int) (this.imageData.getvHeight() * vQuotient), uQuotient, vQuotient);
                }
            }
        }
    }
}
