package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.opengl.GL11;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.ScalableUiElement;
import de.ixsen.minecraft.uilib.elements.core.data.ImageData;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class Image extends ScalableUiElement {

    /** The image that should be drawn into the button */
    protected ImageData imageData;

    public Image(ImageData imageData) {
        super(1);
        this.imageData = imageData;
    }

    public Image(UiContainer parentContainer, ImageData imageData) {
        super(parentContainer, 1);
        this.imageData = imageData;
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glScalef(this.getScale(), this.getScale(), this.getScale());

        Gui.drawModalRectWithCustomSizedTexture(0, 0, this.imageData.getU(), this.imageData.getV(),
                this.imageData.getTextureWidth(), this.imageData.getTextureHeight(), this.imageData.getTextureWidth(),
                this.imageData.getTextureHeight());

        GL11.glPopMatrix();
    }

    public void setResourceLocation(ResourceLocation resourceLocation) {
        this.imageData.setLocation(resourceLocation);

    }
}
