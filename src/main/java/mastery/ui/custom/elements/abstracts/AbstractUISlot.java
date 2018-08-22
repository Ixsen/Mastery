package mastery.ui.custom.elements.abstracts;

import java.awt.image.BufferedImage;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.ReadableDimension;

import com.google.common.base.Preconditions;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.element.AbstractGuiClickable;
import lombok.RequiredArgsConstructor;
import mastery.ui.custom.elements.impl.UISlotGroup;
import mastery.ui.custom.elements.interfaces.IUISlot;
import mastery.ui.custom.elements.interfaces.IUISlotTabGroup;
import mastery.ui.custom.functions.Activatable;
import mastery.ui.custom.functions.ActiveStateRunnable;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public abstract class AbstractUISlot<T extends AbstractUISlot<T>> extends AbstractGuiClickable<T>
        implements IUISlot<T>, Activatable {
    private DynamicTexture texture;
    private ResourceLocation resourceLocation;
    private int u, v;
    private int activeU, activeV;
    private boolean active;
    private int uWidth, vHeight;
    private int textureWidth, textureHeight;
    private UISlotGroup slotGroup;
    private ActiveStateRunnable onStateChange;

    /**
     * Reference to the copied image to prevent it from being garbage collected and subsequently releasing the OpenGL texture.
     */
    private AbstractUISlot<T> copyOf;

    public AbstractUISlot(boolean active) {
        this.active = active;
    }

    public AbstractUISlot(boolean active, UISlotGroup group) {
        this.active = active;
        this.slotGroup = group;
        group.addSlot(this);
    }

    public AbstractUISlot(GuiContainer container, boolean active) {
        super(container);
        this.active = active;
    }

    public AbstractUISlot(GuiContainer container, boolean active, UISlotGroup group) {
        super(container);
        this.active = active;
        this.slotGroup = group;
        group.addSlot(this);
    }

    public AbstractUISlot(AbstractUISlot<T> copyOf) {
        this.texture = copyOf.texture;
        this.resourceLocation = copyOf.resourceLocation;
        this.u = copyOf.u;
        this.v = copyOf.v;
        this.uWidth = copyOf.uWidth;
        this.vHeight = copyOf.vHeight;
        this.textureWidth = copyOf.textureWidth;
        this.textureHeight = copyOf.textureHeight;
        this.active = copyOf.active;
        this.activeU = copyOf.activeU;
        this.activeV = copyOf.activeV;
        this.copyOf = copyOf;
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        if (this.isVisible()) {
            super.draw(renderer, size, renderInfo);
            if (this.texture != null) {
                renderer.bindTexture(this.texture);
            } else {
                renderer.bindTexture(this.resourceLocation);
            }
            int w = size.getWidth();
            int h = size.getHeight();
            if (!this.active) {
                renderer.drawTexturedRect(0, 0, this.u, this.v, w, h, this.uWidth, this.vHeight, this.textureWidth,
                        this.textureHeight);
            } else {
                renderer.drawTexturedRect(0, 0, this.activeU, this.activeV, w, h, this.uWidth, this.vHeight,
                        this.textureWidth, this.textureHeight);
            }
        }
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
    public T setTexture(ResourceLocation resourceLocation) {
        Preconditions.checkState(this.copyOf == null, "Cannot change texture of copy.");
        if (this.texture != null) {
            this.texture.deleteGlTexture();
            this.texture = null;
        }
        this.resourceLocation = resourceLocation;
        this.textureWidth = this.textureHeight = 256;
        return this.getThis();
    }

    @Override
    public T setUV(int u, int v) {
        this.u = u;
        this.v = v;
        return this.getThis();
    }

    @Override
    public T setUVSize(int width, int height) {
        this.uWidth = width;
        this.vHeight = height;
        return this.getThis();
    }

    @Override
    public T setTextureSize(int textureWidth, int textureHeight) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        return null;
    }

    @Override
    public T setActiveUV(int activeU, int activeV) {
        this.activeU = activeU;
        this.activeV = activeV;
        return this.getThis();
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean activeState) {
        this.active = activeState;
        if (this.onStateChange != null) {
            this.onStateChange.avtiveStateChanged(this.active);
        }
    }

    @Override
    public void onActiveChanged(ActiveStateRunnable runnable) {
        this.onStateChange = runnable;
    }

    @Override
    protected void onClick() {
        // Toggle state
        this.setActive(!this.active);

        // Toggle other ui slots in the same group
        if (this.getSlotGroup() != null) {
            this.getSlotGroup().synchUISlots(this);
        }
    }

    @Override
    public IUISlotTabGroup getSlotGroup() {
        return this.slotGroup;
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

}
