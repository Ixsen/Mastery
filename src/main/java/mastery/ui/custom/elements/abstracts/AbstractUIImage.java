package mastery.ui.custom.elements.abstracts;

import java.awt.image.BufferedImage;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.ReadableDimension;

import com.google.common.base.Preconditions;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.element.AbstractGuiElement;
import lombok.RequiredArgsConstructor;
import mastery.ui.custom.elements.interfaces.IUIImage;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public abstract class AbstractUIImage<T extends AbstractUIImage<T>> extends AbstractGuiElement<T>
        implements IUIImage<T> {
    protected DynamicTexture texture;
    protected ResourceLocation resourceLocation;
    protected int u, v;
    protected int uWidth, vHeight;
    protected int textureWidth, textureHeight;

    /**
     * Reference to the copied image to prevent it from being garbage collected and subsequently releasing the OpenGL texture.
     */
    private AbstractUIImage<T> copyOf;

    public AbstractUIImage() {
    }

    public AbstractUIImage(GuiContainer container) {
        super(container);
    }

    public AbstractUIImage(AbstractUIImage<T> copyOf) {
        this.texture = copyOf.texture;
        this.resourceLocation = copyOf.resourceLocation;
        this.u = copyOf.u;
        this.v = copyOf.v;
        this.uWidth = copyOf.uWidth;
        this.vHeight = copyOf.vHeight;
        this.textureWidth = copyOf.textureWidth;
        this.textureHeight = copyOf.textureHeight;
        this.copyOf = copyOf;
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        super.draw(renderer, size, renderInfo);
        if (this.texture != null) {
            renderer.bindTexture(this.texture);
        } else {
            renderer.bindTexture(this.resourceLocation);
        }
        int w = size.getWidth();
        int h = size.getHeight();
        renderer.drawTexturedRect(0, 0, this.u, this.v, w, h, this.uWidth, this.vHeight, this.textureWidth,
                this.textureHeight);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.texture != null && this.copyOf == null) {
            this.getMinecraft().addScheduledTask(new Finalizer(this.texture));
        }
    }

    @Override
    public ReadableDimension calcMinSize() {
        return new Dimension(0, 0);
    }

    @Override
    public T setTexture(BufferedImage img) {
        Preconditions.checkState(this.copyOf == null, "Cannot change texture of copy.");
        this.resourceLocation = null;
        if (this.texture != null) {
            this.texture.deleteGlTexture();
        }
        this.texture = new DynamicTexture(img);
        this.textureWidth = this.uWidth = img.getWidth();
        this.textureHeight = this.vHeight = img.getHeight();
        return this.getThis();
    }

    @Override
    public T setTexture(ResourceLocation resourceLocation, int textureWidth, int textureHeight) {
        Preconditions.checkState(this.copyOf == null, "Cannot change texture of copy.");
        if (this.texture != null) {
            this.texture.deleteGlTexture();
            this.texture = null;
        }
        this.resourceLocation = resourceLocation;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        return this.getThis();
    }

    @Override
    public T setTexture(
            ResourceLocation resourceLocation, int textureWidth, int textureHeight, int u, int v, int width,
            int height) {
        this.setTexture(resourceLocation, textureWidth, textureHeight);
        this.setUV(u, v);
        this.setUVSize(width, height);
        return this.getThis();
    }

    @Override
    public T setU(int u) {
        this.u = u;
        return this.getThis();
    }

    @Override
    public T setV(int v) {
        this.v = v;
        return this.getThis();
    }

    @Override
    public T setUV(int u, int v) {
        this.setU(u);
        return this.setV(v);
    }

    @Override
    public T setUWidth(int width) {
        this.uWidth = width;
        return this.getThis();
    }

    @Override
    public T setVHeight(int height) {
        this.vHeight = height;
        return this.getThis();
    }

    @Override
    public T setUVSize(int width, int height) {
        this.setUWidth(width);
        return this.setVHeight(height);
    }

    /**
     * We use a static class here in order to prevent the inner class from keeping the outer class alive after finalization when still unloading
     * the texture.
     */
    @RequiredArgsConstructor
    private static final class Finalizer implements Runnable {
        private final DynamicTexture texture;

        @Override
        public void run() {
            this.texture.deleteGlTexture();
        }
    }

    @Override
    public T setTextureSize(int textureWidth, int textureHeight) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        return this.getThis();
    }
}
