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
import de.johni0702.minecraft.gui.function.Clickable;
import de.johni0702.minecraft.gui.function.Closeable;
import de.johni0702.minecraft.gui.function.Draggable;
import de.johni0702.minecraft.gui.function.Loadable;
import de.johni0702.minecraft.gui.function.Scrollable;
import de.johni0702.minecraft.gui.function.Tickable;
import de.johni0702.minecraft.gui.function.Typeable;
import de.johni0702.minecraft.gui.utils.MouseUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public abstract class AbstractGuiOverlay<T extends AbstractGuiOverlay<T>> extends AbstractGuiContainer<T> {

    private final UserInputGuiScreen userInputGuiScreen = new UserInputGuiScreen();
    private final EventHandler eventHandler = new EventHandler();
    private boolean visible;
    private Dimension screenSize;
    private boolean mouseVisible;
    private boolean closeable = true;

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void setVisible(boolean visible) {
        if (this.visible != visible) {
            if (visible) {
                this.forEach(Loadable.class).load();
                MinecraftForge.EVENT_BUS.register(this.eventHandler);
            } else {
                this.forEach(Closeable.class).close();
                MinecraftForge.EVENT_BUS.unregister(this.eventHandler);
            }
            this.updateUserInputGui();
        }
        this.visible = visible;
    }

    public boolean isMouseVisible() {
        return this.mouseVisible;
    }

    public void setMouseVisible(boolean mouseVisible) {
        this.mouseVisible = mouseVisible;
        this.updateUserInputGui();
    }

    public boolean isCloseable() {
        return this.closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    /**
     * @see #setAllowUserInput(boolean)
     */
    public boolean isAllowUserInput() {
        return this.userInputGuiScreen.allowUserInput;
    }

    /**
     * Enable/Disable user input for this overlay while the mouse is visible. User input are things like moving the player,
     * attacking/interacting, key bindings but not input into the GUI elements such as text fields. Default for overlays is {@code true} whereas
     * for normal GUI screens it is {@code false}.
     * 
     * @param allowUserInput
     *            {@code true} to allow user input, {@code false} to disallow it
     * @see net.minecraft.client.gui.GuiScreen#allowUserInput
     */
    public void setAllowUserInput(boolean allowUserInput) {
        this.userInputGuiScreen.allowUserInput = allowUserInput;
    }

    private void updateUserInputGui() {
        Minecraft mc = this.getMinecraft();
        if (this.visible) {
            if (this.mouseVisible) {
                if (mc.currentScreen != this.userInputGuiScreen) {
                    mc.displayGuiScreen(this.userInputGuiScreen);
                }
            } else {
                if (mc.currentScreen == this.userInputGuiScreen) {
                    mc.displayGuiScreen(null);
                }
            }
        }
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        super.draw(renderer, size, renderInfo);
        if (this.mouseVisible && renderInfo.layer == this.getMaxLayer()) {
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
                    if (tooltip.isVisible()) {
                        OffsetGuiRenderer eRenderer = new OffsetGuiRenderer(renderer, position, tooltipSize);
                        tooltip.draw(eRenderer, tooltipSize, renderInfo);
                    }
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

    private class EventHandler {
        private MinecraftGuiRenderer renderer;

        @SubscribeEvent
        public void renderOverlay(RenderGameOverlayEvent.Post event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                this.updateRenderer();
                int layers = AbstractGuiOverlay.this.getMaxLayer();
                int mouseX = -1, mouseY = -1;
                if (AbstractGuiOverlay.this.mouseVisible) {
                    Point mouse = MouseUtils.getMousePos();
                    mouseX = mouse.getX();
                    mouseY = mouse.getY();
                }
                for (int layer = 0; layer <= layers; layer++) {
                    AbstractGuiOverlay.this.draw(this.renderer, AbstractGuiOverlay.this.screenSize,
                            new RenderInfo(event.getPartialTicks(), mouseX, mouseY, layer));
                }
            }
        }

        @SubscribeEvent
        public void tickOverlay(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                AbstractGuiOverlay.this.forEach(Tickable.class).tick();
            }
        }

        private void updateRenderer() {
            Minecraft mc = AbstractGuiOverlay.this.getMinecraft();
            ScaledResolution res = new ScaledResolution(mc);
            if (AbstractGuiOverlay.this.screenSize == null
                    || AbstractGuiOverlay.this.screenSize.getWidth() != res.getScaledWidth()
                    || AbstractGuiOverlay.this.screenSize.getHeight() != res.getScaledHeight()) {
                AbstractGuiOverlay.this.screenSize = new Dimension(res.getScaledWidth(), res.getScaledHeight());
                this.renderer = new MinecraftGuiRenderer(res);
            }
        }
    }

    protected class UserInputGuiScreen extends net.minecraft.client.gui.GuiScreen {

        {
            this.allowUserInput = true;
        }

        @Override
        protected void keyTyped(char typedChar, int keyCode) throws IOException {
            AbstractGuiOverlay.this.forEach(Typeable.class).typeKey(MouseUtils.getMousePos(), keyCode, typedChar,
                    isCtrlKeyDown(), isShiftKeyDown());
            if (AbstractGuiOverlay.this.closeable) {
                super.keyTyped(typedChar, keyCode);
            }
        }

        @Override
        protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
            AbstractGuiOverlay.this.forEach(Clickable.class).mouseClick(new Point(mouseX, mouseY), mouseButton);
        }

        @Override
        protected void mouseReleased(int mouseX, int mouseY, int mouseButton) {
            AbstractGuiOverlay.this.forEach(Draggable.class).mouseRelease(new Point(mouseX, mouseY), mouseButton);
        }

        @Override
        protected void mouseClickMove(int mouseX, int mouseY, int mouseButton, long timeSinceLastClick) {
            AbstractGuiOverlay.this.forEach(Draggable.class).mouseDrag(new Point(mouseX, mouseY), mouseButton,
                    timeSinceLastClick);
        }

        @Override
        public void updateScreen() {
            AbstractGuiOverlay.this.forEach(Tickable.class).tick();
        }

        @Override
        public void handleMouseInput() throws IOException {
            super.handleMouseInput();
            if (Mouse.hasWheel() && Mouse.getEventDWheel() != 0) {
                AbstractGuiOverlay.this.forEach(Scrollable.class).scroll(MouseUtils.getMousePos(),
                        Mouse.getEventDWheel());
            }
        }

        @Override
        public void onGuiClosed() {
            AbstractGuiOverlay.this.mouseVisible = false;
        }

        public AbstractGuiOverlay<T> getOverlay() {
            return AbstractGuiOverlay.this;
        }
    }
}
