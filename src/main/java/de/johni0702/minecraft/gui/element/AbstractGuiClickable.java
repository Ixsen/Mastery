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

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.function.Clickable;

public abstract class AbstractGuiClickable<T extends AbstractGuiClickable<T>> extends AbstractGuiElement<T>
        implements Clickable, IGuiClickable<T> {
    private Runnable onClick;

    public AbstractGuiClickable() {
    }

    public AbstractGuiClickable(GuiContainer container) {
        super(container);
    }

    @Override
    public boolean mouseClick(ReadablePoint position, int button) {
        Point pos = new Point(position);
        if (this.getContainer() != null) {
            this.getContainer().convertFor(this, pos);
        }

        if (this.isMouseHovering(pos) && this.isEnabled()) {
            this.onClick();
            return true;
        }
        return false;
    }

    protected boolean isMouseHovering(ReadablePoint pos) {
        if (this.isVisible()) {
            return pos.getX() > 0 && pos.getY() > 0
                    && pos.getX() < this.getLastSize().getWidth() && pos.getY() < this.getLastSize().getHeight();
        } else {
            return false;
        }
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        super.draw(renderer, size, renderInfo);
    }

    protected void onClick() {
        if (this.onClick != null) {
            this.onClick.run();
        }
    }

    @Override
    public T onClick(Runnable onClick) {
        this.onClick = onClick;
        return this.getThis();
    }
}