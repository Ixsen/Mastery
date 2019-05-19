package de.ixsen.minecraft.uilib.elements.basic;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.elements.core.data.UIImageData;

/**
 * @author Subaro
 */
public class UITiledImage extends UIImage {

    public UITiledImage(UIContainer parentContainer, UIImageData imageData) {
        super(parentContainer, imageData);
    }

    public UITiledImage(UIImageData imageData) {
        super(imageData);
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        {
            this.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);

            // Draw Image if set
            if (this.imageData != null) {
                Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
                ReadableDimension size = this.getMinimumSize();
                this.mc.renderEngine.bindTexture(this.imageData.getLocation());
                // Determine rows and columns
                for (int cY = 0; cY < size.getHeight(); cY += this.imageData
                        .getvHeight()) {
                    for (int cX = 0; cX < size.getWidth(); cX += this.imageData
                            .getuWidth()) {
                        int remainingWidth = size.getWidth() - cX;
                        int remainingHeight = size.getHeight() - cY;
                        if (remainingWidth >= this.imageData.getuWidth()
                                && remainingHeight >= this.imageData.getvHeight()) {
                            // Draw a fully image
                            this.drawImage(this.imageData, myGlobalPos.getX() + cX,
                                    myGlobalPos.getY() + cY, this.imageData.getuWidth(),
                                    this.imageData.getvHeight());
                        } else if (remainingWidth < this.imageData.getuWidth()
                                && remainingHeight >= this.imageData.getvHeight()) {
                            float quotient = remainingWidth / (float) this.imageData.getuWidth();
                            this.drawSnippedImage(this.imageData, myGlobalPos.getX() + cX,
                                    myGlobalPos.getY() + cY, (int) (this.imageData.getuWidth() * quotient),
                                    this.imageData.getvHeight(), quotient, 1);
                        } else if (remainingWidth >= this.imageData.getuWidth()
                                && remainingHeight < this.imageData.getvHeight()) {
                            float quotient = remainingHeight / (float) this.imageData.getvHeight();
                            this.drawSnippedImage(this.imageData, myGlobalPos.getX() + cX,
                                    myGlobalPos.getY() + cY, this.imageData.getuWidth(),
                                    (int) (this.imageData.getvHeight() * quotient), 1, quotient);
                        } else {
                            float uQuotient = remainingWidth / (float) this.imageData.getuWidth();
                            float vQuotient = remainingHeight / (float) this.imageData.getvHeight();
                            this.drawSnippedImage(this.imageData, myGlobalPos.getX() + cX,
                                    myGlobalPos.getY() + cY, (int) (this.imageData.getuWidth() * uQuotient),
                                    (int) (this.imageData.getvHeight() * vQuotient), uQuotient, vQuotient);
                        }
                    }
                }
            }
        }
        this.endScaling();
    }

}
