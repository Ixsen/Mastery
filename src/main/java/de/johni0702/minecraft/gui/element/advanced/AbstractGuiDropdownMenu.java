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
package de.johni0702.minecraft.gui.element.advanced;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.lwjgl.util.Color;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.OffsetGuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.element.AbstractComposedGuiElement;
import de.johni0702.minecraft.gui.element.AbstractGuiClickable;
import de.johni0702.minecraft.gui.element.GuiElement;
import de.johni0702.minecraft.gui.element.IGuiClickable;
import de.johni0702.minecraft.gui.function.Clickable;
import de.johni0702.minecraft.gui.layout.VerticalLayout;
import de.johni0702.minecraft.gui.utils.Consumer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.gui.FontRenderer;

public abstract class AbstractGuiDropdownMenu<V, T extends AbstractGuiDropdownMenu<V, T>>
        extends AbstractComposedGuiElement<T> implements IGuiDropdownMenu<V, T>, Clickable {
    private static final ReadableColor OUTLINE_COLOR = new Color(160, 160, 160);

    @Getter
    private int selected;

    @Getter
    private V[] values;

    @Getter
    private boolean opened;

    private Consumer<Integer> onSelection;

    private GuiPanel dropdown;

    private Map<V, IGuiClickable> unmodifiableDropdownEntries;

    private Function<V, String> toString = Object::toString;

    public AbstractGuiDropdownMenu() {
    }

    public AbstractGuiDropdownMenu(GuiContainer container) {
        super(container);
    }

    @Override
    public int getMaxLayer() {
        return this.opened ? 1 : 0;
    }

    @Override
    protected ReadableDimension calcMinSize() {
        FontRenderer fontRenderer = this.getMinecraft().fontRenderer;
        int maxWidth = 0;
        for (V value : this.values) {
            int width = fontRenderer.getStringWidth(this.toString.apply(value));
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return new Dimension(11 + maxWidth + fontRenderer.FONT_HEIGHT, fontRenderer.FONT_HEIGHT + 4);
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        super.draw(renderer, size, renderInfo);
        FontRenderer fontRenderer = this.getMinecraft().fontRenderer;
        if (renderInfo.layer == 0) {
            int width = size.getWidth();
            int height = size.getHeight();

            // Draw box
            renderer.drawRect(0, 0, width, height, OUTLINE_COLOR);
            renderer.drawRect(1, 1, width - 2, height - 2, ReadableColor.BLACK);
            renderer.drawRect(width - height, 0, 1, height, OUTLINE_COLOR);

            // Draw triangle
            int base = height - 6;
            int tHeight = base / 2;
            int x = width - 3 - base / 2;
            int y = height / 2 - 2;
            for (int layer = tHeight; layer > 0; layer--) {
                renderer.drawRect(x - layer, y + tHeight - layer, layer * 2 - 1, 1, OUTLINE_COLOR);
            }

            renderer.drawString(3, height / 2 - fontRenderer.FONT_HEIGHT / 2, ReadableColor.WHITE,
                    this.toString.apply(this.getSelectedValue()));
        } else if (renderInfo.layer == 1) {
            ReadablePoint offsetPoint = new Point(0, size.getHeight());
            ReadableDimension offsetSize = new Dimension(size.getWidth(),
                    (fontRenderer.FONT_HEIGHT + 5) * this.values.length);
            OffsetGuiRenderer offsetRenderer = new OffsetGuiRenderer(renderer, offsetPoint, offsetSize);
            offsetRenderer.startUsing();
            try {
                if (this.dropdown.isVisible()) {
                    this.dropdown.draw(offsetRenderer, offsetSize,
                            renderInfo.offsetMouse(0, offsetPoint.getY()).layer(0));
                }
            } finally {
                offsetRenderer.stopUsing();
            }
        }
    }

    @Override
    public T setValues(V... values) {
        this.values = values;
        this.dropdown = new GuiPanel() {
            @Override
            public void convertFor(GuiElement element, Point point, int relativeLayer) {
                AbstractGuiDropdownMenu parent = AbstractGuiDropdownMenu.this;
                if (parent.getContainer() != null) {
                    parent.getContainer().convertFor(parent, point, relativeLayer + 1);
                }
                point.translate(0, -AbstractGuiDropdownMenu.this.getLastSize().getHeight());
                super.convertFor(element, point, relativeLayer);
            }
        }.setLayout(new VerticalLayout());
        Map<V, IGuiClickable> dropdownEntries = new LinkedHashMap<>();
        for (V value : values) {
            DropdownEntry entry = new DropdownEntry(value);
            dropdownEntries.put(value, entry);
            this.dropdown.addElements(null, entry);
        }
        this.unmodifiableDropdownEntries = Collections.unmodifiableMap(dropdownEntries);
        return this.getThis();
    }

    @Override
    public T setSelected(int selected) {
        this.selected = selected;
        this.onSelection(selected);
        return this.getThis();
    }

    @Override
    public T setSelected(V value) {
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i].equals(value)) {
                return this.setSelected(i);
            }
        }
        throw new IllegalArgumentException("The value " + value + " is not in this dropdown menu.");
    }

    @Override
    public V getSelectedValue() {
        return this.values[this.selected];
    }

    @Override
    public T setOpened(boolean opened) {
        this.opened = opened;
        return this.getThis();
    }

    @Override
    public Collection<GuiElement> getChildren() {
        return this.opened ? Collections.<GuiElement>singletonList(this.dropdown) : Collections.<GuiElement>emptyList();
    }

    @Override
    public T onSelection(Consumer<Integer> consumer) {
        this.onSelection = consumer;
        return this.getThis();
    }

    public void onSelection(Integer value) {
        if (this.onSelection != null) {
            this.onSelection.consume(value);
        }
    }

    @Override
    public boolean mouseClick(ReadablePoint position, int button) {
        Point pos = new Point(position);
        if (this.getContainer() != null) {
            this.getContainer().convertFor(this, pos);
        }

        if (this.isEnabled()) {
            if (this.isMouseHovering(pos)) {
                this.setOpened(!this.isOpened());
                return true;
            }
        }
        return false;
    }

    protected boolean isMouseHovering(ReadablePoint pos) {
        return pos.getX() > 0 && pos.getY() > 0 && pos.getX() < this.getLastSize().getWidth()
                && pos.getY() < this.getLastSize().getHeight();
    }

    @Override
    public Map<V, IGuiClickable> getDropdownEntries() {
        return this.unmodifiableDropdownEntries;
    }

    @Override
    public T setToString(Function<V, String> toString) {
        this.toString = toString;
        return this.getThis();
    }

    @RequiredArgsConstructor
    private class DropdownEntry extends AbstractGuiClickable<DropdownEntry> {
        private final V value;

        @Override
        protected DropdownEntry getThis() {
            return this;
        }

        @Override
        protected ReadableDimension calcMinSize() {
            return new Dimension(0, this.getMinecraft().fontRenderer.FONT_HEIGHT + 5);
        }

        @Override
        public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
            super.draw(renderer, size, renderInfo);
            int width = size.getWidth();
            int height = size.getHeight();

            renderer.drawRect(0, 0, width, height, OUTLINE_COLOR);
            renderer.drawRect(1, 0, width - 2, height - 1, ReadableColor.BLACK);
            renderer.drawString(3, 2, ReadableColor.WHITE, AbstractGuiDropdownMenu.this.toString.apply(this.value));
        }

        @Override
        public boolean mouseClick(ReadablePoint position, int button) {
            boolean result = super.mouseClick(position, button);
            AbstractGuiDropdownMenu.this.setOpened(false);
            return result;
        }

        @Override
        protected void onClick() {
            AbstractGuiDropdownMenu.this.setSelected(this.value);
        }
    }
}