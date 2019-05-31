package de.ixsen.minecraft.uilib.elements.core;

import java.io.IOException;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import de.ixsen.minecraft.uilib.functions.Focusable;
import de.ixsen.minecraft.uilib.layout.GuiLayout;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author Subaro
 */
public abstract class MasteryGuiScreen<CONTAINER_TYPE extends GuiContainer> extends GuiScreen {

    /** Root container containing all elements */
    protected CONTAINER_TYPE screenContainer;
    /** The currently focused element */
    private Focusable focusedElement = null;
    /** Position of the Gui Screen. Mostly 0,0 */
    private Point position;
    /** Current ui element to draw as a tooltip */
    private GuiElement currentTooltip;

    private int eventButton;
    private long lastMouseEvent;
    /** Tracks the number of fingers currently on the screen. Prevents subsequent fingers registering as clicks. */
    private int touchValue;

    public MasteryGuiScreen(Point position) {
        this.position = position;
        this.screenContainer = this.createScreenContainer();
        this.screenContainer.setLayout(this.createLayout());
    }

    protected abstract GuiLayout createLayout();

    /**
     * Create and draw into the screen container in this method
     */
    protected abstract CONTAINER_TYPE createScreenContainer();

    public MasteryGuiScreen() {
        this(new Point(0, 0));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.screenContainer.layoutElements();
        if (this.screenContainer.isVisible()) {
            GL11.glPushMatrix();
            this.screenContainer.draw(this.position.getX(), this.position.getY(), mouseX, mouseY, partialTicks);
            GL11.glPopMatrix();
        }
        // Draw Tooltip
        if (this.currentTooltip != null && this.currentTooltip.isVisible()) {
            GL11.glPushMatrix();
            GL11.glColor4f(1, 1, 1, 1);
            this.currentTooltip.draw(mouseX, mouseY, mouseX, mouseY, partialTicks);
            GL11.glPopMatrix();
        }
    }

    @Override
    public void initGui() {
        this.screenContainer.initGui();
        this.screenContainer.layoutElements();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        // Forward to root container
        this.screenContainer.mouseClicked(mouseX, mouseY, mouseButton);
        if (!this.screenContainer.processFocus(mouseX, mouseY, mouseButton)) {
            this.clearFocus();
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        // Forward to root container
        this.screenContainer.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        // Forward to root container
        this.screenContainer.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        // super checks if ESC is clicked. If clicked the ui is closed
        super.keyTyped(typedChar, keyCode);
        // Forward to root container
        this.screenContainer.keyTyped(typedChar, keyCode);
    }

    /**
     * Sets the focused object.
     *
     * @param element
     *            The element to focus.
     */
    public void setFocusedObject(Focusable element) {
        if (this.focusedElement != null && this.focusedElement != element) {
            this.focusedElement.setFocused(false);
            element.setFocused(true);
            this.focusedElement = element;
        } else if (this.focusedElement == null) {
            element.setFocused(true);
            this.focusedElement = element;
        }
    }

    /**
     * Resets the focus.
     */
    public void clearFocus() {
        if (this.focusedElement != null) {
            this.focusedElement.setFocused(false);
            this.focusedElement = null;
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        // Default mouse behavior
        int i = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int j = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        int k = Mouse.getEventButton();

        if (Mouse.getEventButtonState()) {
            if (this.mc.gameSettings.touchscreen && this.touchValue++ > 0) {
                return;
            }

            this.eventButton = k;
            this.lastMouseEvent = Minecraft.getSystemTime();
            this.mouseClicked(i, j, this.eventButton);
        } else if (k != -1) {
            if (this.mc.gameSettings.touchscreen && --this.touchValue > 0) {
                return;
            }

            this.eventButton = -1;
            this.mouseReleased(i, j, k);
        } else if (this.eventButton != -1 && this.lastMouseEvent > 0L) {
            long l = Minecraft.getSystemTime() - this.lastMouseEvent;
            this.mouseClickMove(i, j, this.eventButton, l);
        }
        // Handle Tooltip
        if (!this.screenContainer.handleTooltip(this, i, j)) {
            this.currentTooltip = null;
        }
    }

    public GuiElement getCurrentTooltip() {
        return this.currentTooltip;
    }

    public void setCurrentTooltip(GuiElement currentTooltip) {
        currentTooltip.screen = this;
        this.currentTooltip = currentTooltip;
    }

}
