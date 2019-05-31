package de.ixsen.minecraft.uilib.elements;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import de.ixsen.minecraft.uilib.elements.core.ScalableGuiElement;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Subaro
 */
public class Fluid extends ScalableGuiElement {

    /** the fluid to render */
    private FluidStack fluidStack;
    /** indicated if the rendered fluid should be flowing */
    private boolean isFlowing;
    public static float FLUID_OFFSET = 0.005f;

    public Fluid(FluidStack fluidStack) {
        super(1f);
        this.fluidStack = fluidStack;
    }

    public Fluid(GuiContainer parentContainer, FluidStack fluidStack) {
        super(parentContainer, 1f);
        this.fluidStack = fluidStack;
    }

    public void setFluidStack(FluidStack fluidStack) {
        this.fluidStack = fluidStack;
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.fluidStack == null) {
            return;
        }

        Point gPos = this.getGlobalPosition(parentX, parentY);
        float quotient = this.fluidStack.amount / 100f;
        int barHeight = (int) (this.getMinimumSize().getHeight() * quotient);
        int yOffset = this.getMinimumSize().getHeight() - barHeight;
        if (this.isFlowing) {
            this.renderTiledFluidFlowing(gPos.getX(), gPos.getY() + yOffset, this.getMinimumSize().getWidth(),
                    barHeight, this.zLevel, this.fluidStack);
        } else {
            this.renderTiledFluidStill(gPos.getX(), gPos.getY() + yOffset, this.getMinimumSize().getWidth(), barHeight,
                    this.zLevel, this.fluidStack);
        }
    }

    public void setFillAmount(int percent) {
        if (percent <= 0) {
            percent = 0;
        } else if (percent >= 100) {
            percent = 100;
        }
        this.fluidStack.amount = percent;
    }

    public int getFillAmount() {
        return this.fluidStack.amount;
    }

    public boolean isFlowing() {
        return this.isFlowing;
    }

    public void setFlowing(boolean flowing) {
        this.isFlowing = flowing;
    }

    // Methods used to draw the fluids, credits to TinkerMod
    /** Renders the given texture tiled into a GUI */
    private void renderTiledTextureAtlas(int x, int y, int width, int height, float depth, TextureAtlasSprite sprite,
            boolean upsideDown) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        this.mc.renderEngine.bindTexture(this.fluidStack.getFluid().getFlowing());

        this.putTiledTextureQuads(worldrenderer, x, y, width, height, depth, sprite, upsideDown);

        tessellator.draw();
    }

    private void renderTiledFluidStill(int x, int y, int width, int height, float depth, FluidStack fluidStack) {
        TextureAtlasSprite fluidSprite = this.mc.getTextureMapBlocks()
                .getAtlasSprite(fluidStack.getFluid().getStill(fluidStack).toString());
        ;
        this.setColorRGBA(fluidStack.getFluid().getColor(fluidStack));
        this.renderTiledTextureAtlas(x, y, width, height, depth, fluidSprite,
                fluidStack.getFluid().isGaseous(fluidStack));
    }

    private void renderTiledFluidFlowing(int x, int y, int width, int height, float depth, FluidStack fluidStack) {
        TextureAtlasSprite fluidSprite = this.mc.getTextureMapBlocks()
                .getAtlasSprite(fluidStack.getFluid().getStill(fluidStack).toString());
        this.setColorRGBA(fluidStack.getFluid().getColor(fluidStack));
        this.renderTiledTextureAtlas(x, y, width, height, depth, fluidSprite,
                fluidStack.getFluid().isGaseous(fluidStack));
    }

    /** Adds a quad to the rendering pipeline. Call startDrawingQuads beforehand. You need to call draw() yourself. */
    private void putTiledTextureQuads(BufferBuilder renderer, int x, int y, int width, int height, float depth,
            TextureAtlasSprite sprite, boolean upsideDown) {
        sprite.initSprite(0, 0, 0, 0, false);
        float u1 = sprite.getMinU();
        float v1 = sprite.getMinV();

        // tile vertically
        do {
            int renderHeight = Math.min(sprite.getIconHeight(), height);
            height -= renderHeight;

            float v2 = sprite.getInterpolatedV(16f * renderHeight / sprite.getIconHeight());

            // we need to draw the quads per width too
            int x2 = x;
            int width2 = width;
            // tile horizontally
            do {
                int renderWidth = Math.min(sprite.getIconWidth(), width2);
                width2 -= renderWidth;

                float u2 = sprite.getInterpolatedU(16f * renderWidth / sprite.getIconWidth());

                if (upsideDown) {
                    renderer.pos(x2, y, depth).tex(u2, v1).endVertex();
                    renderer.pos(x2, y + renderHeight, depth).tex(u2, v2).endVertex();
                    renderer.pos(x2 + renderWidth, y + renderHeight, depth).tex(u1, v2).endVertex();
                    renderer.pos(x2 + renderWidth, y, depth).tex(u1, v1).endVertex();
                } else {
                    renderer.pos(x2, y, depth).tex(u1, v1).endVertex();
                    renderer.pos(x2, y + renderHeight, depth).tex(u1, v2).endVertex();
                    renderer.pos(x2 + renderWidth, y + renderHeight, depth).tex(u2, v2).endVertex();
                    renderer.pos(x2 + renderWidth, y, depth).tex(u2, v1).endVertex();
                }

                x2 += renderWidth;
            } while (width2 > 0);

            y += renderHeight;
        } while (height > 0);
    }

    protected void setColorRGB(int color) {
        this.setColorRGBA(color | 0xff000000);
    }

    private void setColorRGBA(int color) {
        float a = this.alpha(color) / 255.0F;
        float r = this.red(color) / 255.0F;
        float g = this.green(color) / 255.0F;
        float b = this.blue(color) / 255.0F;

        GlStateManager.color(r, g, b, a);
    }

    private int alpha(int c) {
        return c >> 24 & 0xFF;
    }

    private int red(int c) {
        return c >> 16 & 0xFF;
    }

    private int green(int c) {
        return c >> 8 & 0xFF;
    }

    private int blue(int c) {
        return c & 0xFF;
    }

}
