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

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.MinecraftGuiRenderer;
import de.johni0702.minecraft.gui.OffsetGuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.element.GuiElement;
import de.johni0702.minecraft.gui.element.GuiLabel;
import de.johni0702.minecraft.gui.function.Clickable;
import de.johni0702.minecraft.gui.function.Closeable;
import de.johni0702.minecraft.gui.function.Draggable;
import de.johni0702.minecraft.gui.function.Loadable;
import de.johni0702.minecraft.gui.function.Scrollable;
import de.johni0702.minecraft.gui.function.Tickable;
import de.johni0702.minecraft.gui.function.Typeable;
import de.johni0702.minecraft.gui.utils.MouseUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;

public abstract class AbstractGuiScreen<T extends AbstractGuiScreen<T>> extends AbstractGuiContainer<T> {

    private final MinecraftGuiScreen wrapped = new MinecraftGuiScreen();

    private Dimension screenSize;

    @Getter
    @Setter
    private Background background = Background.DEFAULT;

    @Getter
    private boolean enabledRepeatedKeyEvents = true;

    @Getter
    @Setter
    private GuiLabel title;

    public net.minecraft.client.gui.GuiScreen toMinecraft() {
        return this.wrapped;
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        if (renderInfo.layer == 0) {
            switch (this.background) {
            case NONE:
                break;
            case DEFAULT:
                this.wrapped.drawDefaultBackground();
                break;
            case TRANSPARENT:
                int top = 0xc0_10_10_10, bottom = 0xd0_10_10_10;
                renderer.drawRect(0, 0, size.getWidth(), size.getHeight(), top, top, bottom, bottom);
                break;
            case DIRT:
                this.wrapped.drawBackground(0);
                break;
            }
            if (this.title != null) {
                if (this.title.isVisible()) {
                    ReadableDimension titleSize = this.title.getMinSize();
                    int x = this.screenSize.getWidth() / 2 - titleSize.getWidth() / 2;
                    OffsetGuiRenderer eRenderer = new OffsetGuiRenderer(renderer, new Point(x, 10),
                            new Dimension(0, 0));
                    this.title.draw(eRenderer, titleSize, renderInfo);
                }
            }
        }
        super.draw(renderer, size, renderInfo);
        if (renderInfo.layer == this.getMaxLayer()) {
            final GuiElement tooltip = this.forEach(GuiElement.class).getTooltip(renderInfo);
            if (tooltip != null) {
                final ReadableDimension tooltipSize = tooltip.getMinSize();
                int x, y;
                if (renderInfo.mouseX + 8 + tooltipSize.getWidth() < this.screenSize.getWidth()) {
                    x = renderInfo.mouseX + 8;
                } else {
                    x = this.screenSize.getWidth() - tooltipSize.getWidth() - 1;
                }
                if (renderInfo.mouseY + 8 + tooltipSize.getHeight() < this.screenSize.getHeight()) {
                    y = renderInfo.mouseY + 8;
                } else {
                    y = this.screenSize.getHeight() - tooltipSize.getHeight() - 1;
                }
                final ReadablePoint position = new Point(x, y);
                try {
                    OffsetGuiRenderer eRenderer = new OffsetGuiRenderer(renderer, position, tooltipSize);
                    tooltip.draw(eRenderer, tooltipSize, renderInfo);
                } catch (Exception ex) {
                    CrashReport crashReport = CrashReport.makeCrashReport(ex, "Rendering Gui Tooltip");
                    renderInfo.addTo(crashReport);
                    CrashReportCategory category = crashReport.makeCategory("Gui container details");
                    category.addDetail("Container", this::toString);
                    category.addCrashSection("Width", size.getWidth());
                    category.addCrashSection("Height", size.getHeight());
                    category = crashReport.makeCategory("Tooltip details");
                    category.addDetail("Element", tooltip::toString);
                    category.addDetail("Position", position::toString);
                    category.addDetail("Size", tooltipSize::toString);
                    throw new ReportedException(crashReport);
                }
            }
        }
    }

    @Override
    public ReadableDimension getMinSize() {
        return this.screenSize;
    }

    @Override
    public ReadableDimension getMaxSize() {
        return this.screenSize;
    }

    public void setEnabledRepeatedKeyEvents(boolean enableRepeatKeyEvents) {
        this.enabledRepeatedKeyEvents = enableRepeatKeyEvents;
        if (this.wrapped.active) {
            Keyboard.enableRepeatEvents(enableRepeatKeyEvents);
        }
    }

    public void display() {
        this.getMinecraft().displayGuiScreen(this.toMinecraft());
    }

    protected class MinecraftGuiScreen extends net.minecraft.client.gui.GuiScreen {
        private MinecraftGuiRenderer renderer;
        private boolean active;

        @Override
        public void drawScreen(int mouseX, int mouseY, float partialTicks) {
            // The Forge loading screen apparently leaves one of the textures of the GlStateManager in an
            // incorrect state which can cause the whole screen to just remain white. This is a workaround.
            GlStateManager.disableTexture2D();
            GlStateManager.enableTexture2D();

            int layers = AbstractGuiScreen.this.getMaxLayer();
            for (int layer = 0; layer <= layers; layer++) {
                AbstractGuiScreen.this.draw(this.renderer, AbstractGuiScreen.this.screenSize,
                        new RenderInfo(partialTicks, mouseX, mouseY, layer));
            }
        }

        @Override
        protected void keyTyped(char typedChar, int keyCode) throws IOException {
            if (!AbstractGuiScreen.this.forEach(Typeable.class).typeKey(MouseUtils.getMousePos(), keyCode, typedChar,
                    isCtrlKeyDown(), isShiftKeyDown())) {
                super.keyTyped(typedChar, keyCode);
            }
        }

        @Override
        protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
            AbstractGuiScreen.this.forEach(Clickable.class).mouseClick(new Point(mouseX, mouseY), mouseButton);
        }

        @Override
        protected void mouseReleased(int mouseX, int mouseY, int mouseButton) {
            AbstractGuiScreen.this.forEach(Draggable.class).mouseRelease(new Point(mouseX, mouseY), mouseButton);
        }

        @Override
        protected void mouseClickMove(int mouseX, int mouseY, int mouseButton, long timeSinceLastClick) {
            AbstractGuiScreen.this.forEach(Draggable.class).mouseDrag(new Point(mouseX, mouseY), mouseButton,
                    timeSinceLastClick);
        }

        @Override
        public void updateScreen() {
            AbstractGuiScreen.this.forEach(Tickable.class).tick();
        }

        @Override
        public void handleMouseInput() throws IOException {
            super.handleMouseInput();
            if (Mouse.hasWheel() && Mouse.getEventDWheel() != 0) {
                AbstractGuiScreen.this.forEach(Scrollable.class).scroll(MouseUtils.getMousePos(),
                        Mouse.getEventDWheel());
            }
        }

        @Override
        public void onGuiClosed() {
            AbstractGuiScreen.this.forEach(Closeable.class).close();
            this.active = false;
            if (AbstractGuiScreen.this.enabledRepeatedKeyEvents) {
                Keyboard.enableRepeatEvents(false);
            }
        }

        @Override
        public void initGui() {
            this.active = false;
            if (AbstractGuiScreen.this.enabledRepeatedKeyEvents) {
                Keyboard.enableRepeatEvents(true);
            }
            AbstractGuiScreen.this.screenSize = new Dimension(this.width, this.height);
            this.renderer = new MinecraftGuiRenderer(new ScaledResolution(this.mc));
            AbstractGuiScreen.this.forEach(Loadable.class).load();
        }

        public T getWrapper() {
            return AbstractGuiScreen.this.getThis();
        }
    }

    public enum Background {
        NONE, DEFAULT, TRANSPARENT, DIRT;
    }
}
