package masteryUI.elements.core;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import masteryUI.functions.Focusable;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author Subaro
 */
public class UIMCScreen extends GuiScreen {

    /** Root container containing all elements */
    protected UIContainer screenContainer;
    /** The currently focused element */
    private Focusable focusedElement = null;
    /** Position of the Gui Screen. Mostly 0,0 */
    public Point position;

    public UIMCScreen(Point position) {
        this.position = position;
        this.screenContainer = new UIContainer(this) {

            @Override
            public void initGui() {
                super.initGui();
            }
        };
    }

    public UIMCScreen() {
        this.position = new Point(0, 0);
        this.screenContainer = new UIContainer(this) {
            @Override
            public void initGui() {
                super.initGui();
            }
        };
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glPushMatrix();
        if (this.screenContainer.isVisible()) {
            this.screenContainer.draw(this.position.getX(), this.position.getY(), mouseX, mouseY, partialTicks);
        }
        GL11.glPopMatrix();
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

}
