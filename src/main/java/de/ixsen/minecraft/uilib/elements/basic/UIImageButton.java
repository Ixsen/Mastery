package de.ixsen.minecraft.uilib.elements.basic;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.elements.core.data.UIImageData;

/**
 * Represents a button that draws a given image into the button.
 *
 * @author Subaro
 */
public class UIImageButton extends UIButton {

    /** The image that should be drawn into the button */
    private UIImageData imageData;

    public UIImageButton(UIImageData imageData) {
        super("");
        this.imageData = imageData;
    }

    public UIImageButton(UIContainer parentContainer, UIImageData imageData) {
        super(parentContainer, "");
        this.imageData = imageData;
    }

    @Override
    public void drawBackground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        // Draw Default Background
        super.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);
        // Draw Image if set
        if (this.imageData != null) {
            Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
            ReadableDimension size = this.getMinimumSize();
            int drawSize = Math.min(size.getWidth() - 8, size.getHeight() - 8);
            int imageX = myGlobalPos.getX() + size.getWidth() / 2 - drawSize / 2;
            int imageY = myGlobalPos.getY() + size.getHeight() / 2 - drawSize / 2;
            this.drawImageBind(this.imageData, imageX, imageY, drawSize, drawSize);
        }
    }

}
