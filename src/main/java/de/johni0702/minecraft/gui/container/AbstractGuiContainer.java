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

import static com.google.common.base.Preconditions.checkState;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.OffsetGuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.element.AbstractComposedGuiElement;
import de.johni0702.minecraft.gui.element.ComposedGuiElement;
import de.johni0702.minecraft.gui.element.GuiElement;
import de.johni0702.minecraft.gui.layout.HorizontalLayout;
import de.johni0702.minecraft.gui.layout.Layout;
import de.johni0702.minecraft.gui.layout.LayoutData;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;

public abstract class AbstractGuiContainer<T extends AbstractGuiContainer<T>> extends AbstractComposedGuiElement<T>
        implements GuiContainer<T> {

    private static final Layout DEFAULT_LAYOUT = new HorizontalLayout();

    private final Map<GuiElement, LayoutData> elements = new LinkedHashMap<>();

    private Map<GuiElement, Pair<ReadablePoint, ReadableDimension>> layedOutElements;

    private Layout layout = DEFAULT_LAYOUT;

    private ReadableColor backgroundColor;

    public AbstractGuiContainer() {
    }

    public AbstractGuiContainer(GuiContainer container) {
        super(container);
    }

    @Override
    public T setLayout(Layout layout) {
        this.layout = layout;
        return this.getThis();
    }

    @Override
    public Layout getLayout() {
        return this.layout;
    }

    @Override
    public void convertFor(GuiElement element, Point point) {
        this.convertFor(element, point, element.getLayer());
    }

    @Override
    public void convertFor(GuiElement element, Point point, int relativeLayer) {
        checkState(this.layedOutElements != null, "Cannot convert position unless rendered at least once.");
        Pair<ReadablePoint, ReadableDimension> pair = this.layedOutElements.get(element);
        checkState(pair != null, "Element " + element + " not part of " + this);
        ReadablePoint pos = pair.getKey();
        if (this.getContainer() != null) {
            this.getContainer().convertFor(this, point, relativeLayer + this.getLayer());
        }
        point.translate(-pos.getX(), -pos.getY());
    }

    @Override
    public Collection<GuiElement> getChildren() {
        return Collections.unmodifiableCollection(this.elements.keySet());
    }

    @Override
    public Map<GuiElement, LayoutData> getElements() {
        return Collections.unmodifiableMap(this.elements);
    }

    @Override
    public T addElements(LayoutData layoutData, GuiElement... elements) {
        if (layoutData == null) {
            layoutData = LayoutData.NONE;
        }
        for (GuiElement element : elements) {
            this.elements.put(element, layoutData);
            element.setContainer(this);
        }
        return this.getThis();
    }

    @Override
    public T removeElement(GuiElement element) {
        if (this.elements.remove(element) != null) {
            element.setContainer(null);
            if (this.layedOutElements != null) {
                this.layedOutElements.remove(element);
            }
        }
        return this.getThis();
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        super.draw(renderer, size, renderInfo);
        try {
            this.layedOutElements = this.layout.layOut(this, size);
        } catch (Exception ex) {
            CrashReport crashReport = CrashReport.makeCrashReport(ex, "Gui Layout");
            renderInfo.addTo(crashReport);
            CrashReportCategory category = crashReport.makeCategory("Gui container details");
            category.addDetail("Container", this::toString);
            category.addDetail("Layout", this.layout::toString);
            throw new ReportedException(crashReport);
        }
        if (this.backgroundColor != null && renderInfo.getLayer() == 0) {
            renderer.drawRect(0, 0, size.getWidth(), size.getHeight(), this.backgroundColor);
        }
        for (final Map.Entry<GuiElement, Pair<ReadablePoint, ReadableDimension>> e : this.layedOutElements.entrySet()) {
            GuiElement element = e.getKey();
            boolean strict;
            if (element instanceof ComposedGuiElement) {
                if (((ComposedGuiElement) element).getMaxLayer() < renderInfo.layer) {
                    continue;
                }
                strict = renderInfo.layer == 0;
            } else {
                if (element.getLayer() != renderInfo.layer) {
                    continue;
                }
                strict = true;
            }
            final ReadablePoint ePosition = e.getValue().getLeft();
            final ReadableDimension eSize = e.getValue().getRight();
            try {
                OffsetGuiRenderer eRenderer = new OffsetGuiRenderer(renderer, ePosition, eSize, strict);
                eRenderer.startUsing();
                e.getKey().draw(eRenderer, eSize, renderInfo.offsetMouse(ePosition.getX(), ePosition.getY())
                        .layer(renderInfo.getLayer() - e.getKey().getLayer()));
                eRenderer.stopUsing();
            } catch (Exception ex) {
                CrashReport crashReport = CrashReport.makeCrashReport(ex, "Rendering Gui");
                renderInfo.addTo(crashReport);
                CrashReportCategory category = crashReport.makeCategory("Gui container details");
                category.addDetail("Container", this::toString);
                category.addCrashSection("Width", size.getWidth());
                category.addCrashSection("Height", size.getHeight());
                category.addDetail("Layout", this.layout::toString);
                category = crashReport.makeCategory("Gui element details");
                category.addDetail("Element", () -> e.getKey().toString());
                category.addDetail("Position", ePosition::toString);
                category.addDetail("Size", eSize::toString);
                if (e.getKey() instanceof GuiContainer) {
                    category.addDetail("Layout", () -> ((GuiContainer) e.getKey()).getLayout().toString());
                }
                throw new ReportedException(crashReport);
            }
        }
    }

    @Override
    public ReadableDimension calcMinSize() {
        return this.layout.calcMinSize(this);
    }

    @Override
    public T sortElements() {
        this.sortElements(new Comparator<GuiElement>() {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(GuiElement o1, GuiElement o2) {
                if (o1 instanceof Comparable && o2 instanceof Comparable) {
                    return ((Comparable) o1).compareTo(o2);
                }
                return o1.hashCode() - o2.hashCode();
            }
        });
        return this.getThis();
    }

    @Override
    public T sortElements(final Comparator<GuiElement> comparator) {
        Ordering<Map.Entry<GuiElement, LayoutData>> ordering = new Ordering<Map.Entry<GuiElement, LayoutData>>() {
            @Override
            public int compare(Map.Entry<GuiElement, LayoutData> left, Map.Entry<GuiElement, LayoutData> right) {
                return comparator.compare(left.getKey(), right.getKey());
            }
        };
        if (!ordering.isOrdered(this.elements.entrySet())) {
            ImmutableList<Map.Entry<GuiElement, LayoutData>> sorted = ordering
                    .immutableSortedCopy(this.elements.entrySet());
            this.elements.clear();
            for (Map.Entry<GuiElement, LayoutData> entry : sorted) {
                this.elements.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getThis();
    }

    @Override
    public ReadableColor getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override
    public T setBackgroundColor(ReadableColor backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this.getThis();
    }
}
