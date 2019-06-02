package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.data.ImageData;

/**
 * @author Subaro
 */
public class TiledImage extends Image {

    public TiledImage(UiContainer parentContainer, ImageData imageData) {
        super(parentContainer, imageData);
    }

    public TiledImage(ImageData imageData) {
        super(imageData);
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.imageData == null) {
            return;
        }

        ReadableDimension size = this.getMinimumSize();
        this.mc.renderEngine.bindTexture(this.imageData.getLocation());
        // Determine rows and columns
        for (int cY = 0; cY < size.getHeight(); cY += this.imageData.getvHeight()) {
            for (int cX = 0; cX < size.getWidth(); cX += this.imageData.getuWidth()) {
                int remainingWidth = size.getWidth() - cX;
                int remainingHeight = size.getHeight() - cY;
                if (remainingWidth >= this.imageData.getuWidth() && remainingHeight >= this.imageData.getvHeight()) {
                    // Draw a fully image
                    this.drawImage(this.imageData, this.getGlobalPosition().getX() + cX,
                            this.getGlobalPosition().getY() + cY, this.imageData.getuWidth(),
                            this.imageData.getvHeight());
                } else if (remainingWidth < this.imageData.getuWidth()
                        && remainingHeight >= this.imageData.getvHeight()) {
                    float quotient = remainingWidth / (float) this.imageData.getuWidth();
                    this.drawSnippedImage(this.imageData, this.getGlobalPosition().getX() + cX,
                            this.getGlobalPosition().getY() + cY, (int) (this.imageData.getuWidth() * quotient),
                            this.imageData.getvHeight(), quotient, 1);
                } else if (remainingWidth >= this.imageData.getuWidth()
                        && remainingHeight < this.imageData.getvHeight()) {
                    float quotient = remainingHeight / (float) this.imageData.getvHeight();
                    this.drawSnippedImage(this.imageData, this.getGlobalPosition().getX() + cX,
                            this.getGlobalPosition().getY() + cY, this.imageData.getuWidth(),
                            (int) (this.imageData.getvHeight() * quotient), 1, quotient);
                } else {
                    float uQuotient = remainingWidth / (float) this.imageData.getuWidth();
                    float vQuotient = remainingHeight / (float) this.imageData.getvHeight();
                    this.drawSnippedImage(this.imageData, this.getGlobalPosition().getX() + cX,
                            this.getGlobalPosition().getY() + cY, (int) (this.imageData.getuWidth() * uQuotient),
                            (int) (this.imageData.getvHeight() * vQuotient), uQuotient, vQuotient);
                }
            }
        }
    }

}
