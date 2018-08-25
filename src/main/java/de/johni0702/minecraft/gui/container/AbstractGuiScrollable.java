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
package de.johni0702.minecraft.gui.container;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;
import org.lwjgl.util.WritablePoint;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.OffsetGuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.element.GuiElement;
import de.johni0702.minecraft.gui.function.Scrollable;
import lombok.Getter;

public abstract class AbstractGuiScrollable<T extends AbstractGuiScrollable<T>> extends AbstractGuiContainer<T>
        implements Scrollable {
    private int offsetX, offsetY;
    @Getter
    private final ReadablePoint negativeOffset = new ReadablePoint() {
        @Override
        public int getX() {
            return -AbstractGuiScrollable.this.offsetX;
        }

        @Override
        public int getY() {
            return -AbstractGuiScrollable.this.offsetY;
        }

        @Override
        public void getLocation(WritablePoint dest) {
            dest.setLocation(this.getX(), this.getY());
        }
    };

    private Direction scrollDirection = Direction.VERTICAL;

    protected ReadableDimension lastRenderSize;

    public AbstractGuiScrollable() {
    }

    public AbstractGuiScrollable(GuiContainer container) {
        super(container);
    }

    @Override
    public void convertFor(GuiElement element, Point point, int relativeLayer) {
        super.convertFor(element, point, relativeLayer);
        point.translate(this.offsetX, this.offsetY);
        if (!(relativeLayer > 0 || point.getX() > 0 && point.getX() < this.lastRenderSize.getWidth() && point.getY() > 0
                && point.getY() < this.lastRenderSize.getHeight())) {
            point.setLocation(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        int width = size.getWidth();
        int height = size.getHeight();
        this.lastRenderSize = size;
        size = super.calcMinSize();
        size = new Dimension(Math.max(width, size.getWidth()), Math.max(height, size.getHeight()));
        renderInfo = renderInfo.offsetMouse(-this.offsetX, -this.offsetY);

        OffsetGuiRenderer offsetRenderer = new OffsetGuiRenderer(renderer, this.negativeOffset, size,
                renderInfo.layer == 0);
        offsetRenderer.startUsing();
        super.draw(offsetRenderer, size, renderInfo);
        offsetRenderer.stopUsing();
    }

    @Override
    public ReadableDimension calcMinSize() {
        return new Dimension(0, 0);
    }

    @Override
    public boolean scroll(ReadablePoint mousePosition, int dWheel) {
        Point mouse = new Point(mousePosition);
        if (this.getContainer() != null) {
            this.getContainer().convertFor(this, mouse);
        }
        if (mouse.getX() > 0 && mouse.getY() > 0 && mouse.getX() < this.lastRenderSize.getWidth()
                && mouse.getY() < this.lastRenderSize.getHeight()) {
            // Reduce scrolling speed but make sure it is never rounded to 0
            dWheel = (int) Math.copySign(Math.ceil(Math.abs(dWheel) / 4.0), dWheel);
            if (this.scrollDirection == Direction.HORIZONTAL) {
                this.scrollX(dWheel);
            } else {
                this.scrollY(dWheel);
            }
            return true;
        }
        return false;
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public T setOffsetX(int offsetX) {
        this.offsetX = offsetX;
        return this.getThis();
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public T setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        return this.getThis();
    }

    public Direction getScrollDirection() {
        return this.scrollDirection;
    }

    public T setScrollDirection(Direction scrollDirection) {
        this.scrollDirection = scrollDirection;
        return this.getThis();
    }

    public T scrollX(int dPixel) {
        this.offsetX = Math.max(0,
                Math.min(super.calcMinSize().getWidth() - this.lastRenderSize.getWidth(), this.offsetX - dPixel));
        return this.getThis();
    }

    public T scrollY(int dPixel) {
        this.offsetY = Math.max(0,
                Math.min(super.calcMinSize().getHeight() - this.lastRenderSize.getHeight(), this.offsetY - dPixel));
        return this.getThis();
    }

    public enum Direction {
        VERTICAL, HORIZONTAL
    }
}
