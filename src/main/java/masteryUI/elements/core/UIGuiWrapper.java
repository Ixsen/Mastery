package masteryUI.elements.core;

import org.lwjgl.opengl.GL11;

import masteryUI.elements.core.data.UIImageData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

/**
 * This class helps to separate the functionality of the UIElements from the draw methods needed to draw objects. This class contains
 * fundamental self-created draw methods.
 *
 * @author Subaro
 */
public abstract class UIGuiWrapper extends Gui {

    /** Reference to the current Minecraft instance. */
    protected Minecraft mc;

    protected float currentScaleFactor = 1;

    public UIGuiWrapper() {
        this.mc = Minecraft.getMinecraft();
    }

    /**
     * Used to create a drawing area that clips all elements that are outside. CAUTION you always need to end the scissoring with the
     * endScissors method
     */
    public void startScissors(UIMCScreen screen, int x, int y, int width, int height) {
        float localFactor = this.currentScaleFactor != 1 ? this.currentScaleFactor : 1;

        // Calculate the ratios
        float rx = (float) this.mc.displayWidth / (float) screen.width;
        float ry = (float) this.mc.displayHeight / (float) screen.height;
        int posX = (int) (x * localFactor * rx);
        int posY = (int) (this.mc.displayHeight - (y * localFactor + height * localFactor) * ry);
        int posWidth = (int) (width * localFactor * rx);
        int posHeight = (int) (height * localFactor * ry);

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(posX, posY, posWidth, posHeight);
    }

    /**
     * Ends the scissoring.
     */
    public void endScissors() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    /**
     * Scales the complete gui. Has a start and end method. Using two start isn`t working.
     *
     * @param factor
     *            The Scale factor
     */
    public void startScaling(float factor) {
        if (this.currentScaleFactor == 1) {
            GL11.glScalef(factor, factor, factor);
            this.currentScaleFactor = factor;
        }
    }

    /**
     * Ends the scaling area. Is the closing method to startScaling.
     */
    public void endScaling() {
        if (this.currentScaleFactor != 1) {
            GL11.glScalef(1f / this.currentScaleFactor, 1f / this.currentScaleFactor, 1f / this.currentScaleFactor);
            this.currentScaleFactor = 1;
        }
    }

    /**
     * Draws an image to the screen. The texture is especially binded. If that is not wanted use drawImage(...).
     */
    protected void drawImageBind(UIImageData data, int x, int y, int width, int height) {
        GL11.glEnable(GL11.GL_BLEND);
        if (data != null) {
            this.mc.renderEngine.bindTexture(data.getLocation());
            Gui.drawScaledCustomSizeModalRect(x, y, data.getU(), data.getV(), data.getuWidth(), data.getvHeight(), width,
                    height, data.getTextureWidth(), data.getTextureHeight());
        }
        GL11.glDisable(GL11.GL_BLEND);
    }

    /**
     * Draws an image to the screen. The texture is NOT binded. If that is not wanted use drawImageBind(...)
     */
    protected void drawImage(UIImageData data, int x, int y, int width, int height) {
        GL11.glEnable(GL11.GL_BLEND);
        if (data != null) {
            Gui.drawScaledCustomSizeModalRect(x, y, data.getU(), data.getV(), data.getuWidth(), data.getvHeight(), width,
                    height, data.getTextureWidth(), data.getTextureHeight());
        }
        GL11.glDisable(GL11.GL_BLEND);
    }

    /**
     * Draws an image to the screen. The texture is NOT binded. If that is not wanted use drawImageBind(...)
     */
    protected void drawSnippedImage(UIImageData data, int x, int y, int width, int height, float uFactor,
            float vFactor) {
        GL11.glEnable(GL11.GL_BLEND);
        if (data != null) {
            Gui.drawScaledCustomSizeModalRect(x, y, data.getU(), data.getV(), (int) (data.getuWidth() * uFactor),
                    (int) (data.getvHeight() * vFactor), width, height, data.getTextureWidth(),
                    data.getTextureHeight());
        }
        GL11.glDisable(GL11.GL_BLEND);
    }

}
