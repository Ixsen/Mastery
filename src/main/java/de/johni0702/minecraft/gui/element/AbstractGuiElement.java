/*
 * This file is part of jGui API, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016 johni0702 <https://github.com/johni0702>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.johni0702.minecraft.gui.element;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.GuiContainer;
import lombok.AccessLevel;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractGuiElement<T extends AbstractGuiElement<T>> implements GuiElement<T> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation("jgui", "gui.png");

    private boolean visible = true;
    private VisibilityRunnable onVisible;

    @Getter
    private final Minecraft minecraft = Minecraft.getMinecraft();

    @Getter
    private GuiContainer container;

    private GuiElement tooltip;

    @Getter
    private boolean enabled = true;

    protected Dimension minSize, maxSize;

    /**
     * The last size this element was render at layer 0. May be {@code null} when this element has not yet been rendered.
     */
    @Getter(AccessLevel.PROTECTED)
    private ReadableDimension lastSize;

    public AbstractGuiElement() {
    }

    public AbstractGuiElement(GuiContainer container) {
        container.addElements(null, this);
    }

    protected abstract T getThis();

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        if (this.isVisible()) {
            if (renderInfo.layer == 0) {
                this.lastSize = size;
            }
        }
    }

    @Override
    public T setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this.getThis();
    }

    @Override
    public T setEnabled() {
        return this.setEnabled(true);
    }

    @Override
    public T setDisabled() {
        return this.setEnabled(false);
    }

    @Override
    public GuiElement getTooltip(RenderInfo renderInfo) {
        if (this.tooltip != null && this.lastSize != null) {
            Point mouse = new Point(renderInfo.mouseX, renderInfo.mouseY);
            if (this.container != null) {
                this.container.convertFor(this, mouse);
            }
            if (mouse.getX() > 0 && mouse.getY() > 0 && mouse.getX() < this.lastSize.getWidth()
                    && mouse.getY() < this.lastSize.getHeight()) {
                return this.tooltip;
            }
        }
        return null;
    }

    @Override
    public T setTooltip(GuiElement tooltip) {
        this.tooltip = tooltip;
        return this.getThis();
    }

    @Override
    public T setContainer(GuiContainer container) {
        this.container = container;
        return this.getThis();
    }

    public T setMinSize(ReadableDimension minSize) {
        this.minSize = new Dimension(minSize);
        return this.getThis();
    }

    @Override
    public T setMaxSize(ReadableDimension maxSize) {
        this.maxSize = new Dimension(maxSize);
        return this.getThis();
    }

    public T setSize(ReadableDimension size) {
        this.setMinSize(size);
        return this.setMaxSize(size);
    }

    public T setSize(int width, int height) {
        return this.setSize(new Dimension(width, height));
    }

    public T setWidth(int width) {
        if (this.minSize == null) {
            this.minSize = new Dimension(width, 0);
        } else {
            this.minSize.setWidth(width);
        }
        if (this.maxSize == null) {
            this.maxSize = new Dimension(width, Integer.MAX_VALUE);
        } else {
            this.maxSize.setWidth(width);
        }
        return this.getThis();
    }

    public T setHeight(int height) {
        if (this.minSize == null) {
            this.minSize = new Dimension(0, height);
        } else {
            this.minSize.setHeight(height);
        }
        if (this.maxSize == null) {
            this.maxSize = new Dimension(Integer.MAX_VALUE, height);
        } else {
            this.maxSize.setHeight(height);
        }
        return this.getThis();
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    public ReadableDimension getMinSize() {
        ReadableDimension calcSize = this.calcMinSize();
        if (this.minSize == null) {
            return calcSize;
        } else {
            if (this.minSize.getWidth() >= calcSize.getWidth() && this.minSize.getHeight() >= calcSize.getHeight()) {
                return this.minSize;
            } else {
                return new Dimension(Math.max(calcSize.getWidth(), this.minSize.getWidth()),
                        Math.max(calcSize.getHeight(), this.minSize.getHeight()));
            }
        }
    }

    protected abstract ReadableDimension calcMinSize();

    @Override
    public ReadableDimension getMaxSize() {
        return this.maxSize == null ? new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE) : this.maxSize;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
        this.onVisibleChange(this.visible);
    }

    @Override
    public boolean isVisible() {
        return this.container == null ? this.visible : this.visible && this.container.isVisible();
    }

    @Override
    public void onVisibleChange(VisibilityRunnable onVisible) {
        this.onVisible = onVisible;
    }

    @Override
    public void onVisibleChange(boolean newValue) {
        if (this.onVisible != null) {
            this.onVisible.run(newValue);
        }
    }

    public interface VisibilityRunnable {
        public void run(boolean value);
    }

}
