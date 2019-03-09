package mastery.oldui.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UIImage extends GuiButton {

    private ResourceLocation resource;
    private int textureWidth;
    private int textureHeight;
    private float scaleFactor;
    private double transparency;

    public UIImage(int id, int x, int y, int width, int height, ResourceLocation resource, int textureWidth,
            int textureHeight, float scaleFactor, double transparency) {
        super(id, x, y, width, height, "");
        this.resource = resource;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.scaleFactor = scaleFactor;
        if (transparency > 1) {
            transparency = 1;
        }
        if (transparency < 0) {
            transparency = 0;
        }
        this.transparency = transparency;
    }

    public UIImage(int id, int x, int y, int width, int height, ResourceLocation resource, int textureWidth,
            int textureHeight, float scaleFactor, double transparency, String label) {
        super(id, x, y, width, height, label);
        this.resource = resource;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.scaleFactor = scaleFactor;
        if (transparency > 1) {
            transparency = 1;
        }
        if (transparency < 0) {
            transparency = 0;
        }
        this.transparency = transparency;
    }

    public ResourceLocation getResource() {
        return this.resource;
    }

    public int getTextureWidth() {
        return this.textureWidth;
    }

    public int getTextureHeight() {
        return this.textureHeight;
    }

    public float getScaleFactor() {
        return this.scaleFactor;
    }

    public double getTransparency() {
        return this.transparency;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            // Draw the icon for the mastery
            GL11.glPushMatrix();
            mc.renderEngine.bindTexture(this.resource);
            GL11.glScalef(this.scaleFactor, this.scaleFactor, 1);
            GL11.glColor4d(1, 1, 1, this.transparency);
            drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, this.width, this.height, this.textureWidth,
                    this.textureHeight);
            if (!this.displayString.equals("")) {
                this.drawString(mc.fontRenderer, this.displayString, this.x + 5, this.y + 5, 0xFFFFFFFF);
            }
            GL11.glPopMatrix();
        }
    }
}