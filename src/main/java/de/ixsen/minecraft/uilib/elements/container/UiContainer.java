package de.ixsen.minecraft.uilib.elements.container;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.GL11;

import de.ixsen.minecraft.uilib.elements.core.UiElement;
import de.ixsen.minecraft.uilib.elements.core.UiScreen;
import de.ixsen.minecraft.uilib.functions.Clickable;
import de.ixsen.minecraft.uilib.functions.Draggable;
import de.ixsen.minecraft.uilib.functions.Focusable;
import de.ixsen.minecraft.uilib.functions.Typeable;
import de.ixsen.minecraft.uilib.layout.HorizontalLayout;
import de.ixsen.minecraft.uilib.layout.UiLayout;

/**
 * @author Subaro
 */
public abstract class UiContainer extends UiElement {

    /** The default layout used by ui containers. */
    private static UiLayout DEFAULT_LAYOUT = new HorizontalLayout();
    /** List containg all the ui elemnts that are assigned to this container. */
    protected final List<UiElement> containedElements;

    /**
     * Constructor for the root container element.
     *
     * @param screen
     *            Screen to pass.
     */
    public UiContainer(UiScreen screen) {
        super();
        this.screen = screen;
        this.containedElements = new ArrayList<>();
    }

    public UiContainer() {
        super();
        this.containedElements = new ArrayList<>();
    }

    public UiContainer(UiContainer parentContainer) {
        super(parentContainer);
        this.containedElements = new ArrayList<>();
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        for (UiElement element : this.containedElements) {
            if (element.isVisible()) {
                // Only draw if visible
                GL11.glPushMatrix();
                element.draw(this.getGlobalPosition().getX(), this.getGlobalPosition().getY(), mouseX, mouseY,
                        partialTicks);
                GL11.glColor4f(1f, 1f, 1f, 1f);
                GL11.glPopMatrix();
            }
        }
    }

    /**
     * Add one or more child to this container with the same default layout data of the set layout.
     *
     * @param elements
     *            UIElements to add.
     */
    public void addElements(UiElement... elements) {
        for (UiElement element : elements) {
            this.containedElements.add(element);
            element.setScreen(this.screen);
        }
    }

    /**
     * Removes the child for this container.
     *
     * @param element
     *            UiElement to remove.
     */
    public void removeElement(UiElement element) {
        this.containedElements.remove(element);
    }

    /**
     * Layouts all elements and sets their position.
     */
    public abstract void layoutElements();

    /**
     * Handles the forwarding of the mouse click events to the right elements.
     */
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        // Check the containing ui elements
        for (UiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UiContainer
            if (element instanceof UiContainer) {
                if (((UiContainer) element).mouseClicked(mouseX, mouseY, mouseButton)) {
                    return true;
                }
            }
            // Fire Click-Event
            if (element instanceof Clickable) {
                if (element.isMouseHovering(mouseX, mouseY)) {
                    if (((Clickable) element).onClick(mouseX, mouseY, mouseButton)) {
                        return true;
                    }
                }
            }
        }
        // Check the container itself
        if (this instanceof Clickable) {
            if (this.isMouseHovering(mouseX, mouseY)) {
                if (((Clickable) this).onClick(mouseX, mouseY, mouseButton)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Handles the forwarding of the mouse click events to the right elements.
     */
    public boolean processFocus(int mouseX, int mouseY, int mouseButton) {
        // Only focus when left-clicking
        if (mouseButton != 0) {
            return true;
        }
        // Check the containing ui elements
        for (UiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UiContainer
            if (element instanceof UiContainer) {
                if (((UiContainer) element).processFocus(mouseX, mouseY, mouseButton)) {
                    return true;
                }
            }
            // Check the element of focus
            if (element instanceof Focusable) {
                if (element.isMouseHovering(mouseX, mouseY)) {
                    this.screen.setFocusedObject((Focusable) element);
                    return true;
                }
            }
        }
        // Check the container itself
        if (this instanceof Focusable) {
            if (this.isMouseHovering(mouseX, mouseY)) {
                this.screen.setFocusedObject((Focusable) this);
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the forwarding of the mouse click events to the right elements.
     */
    public boolean mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        // Check the containing ui elements
        for (UiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UiContainer
            if (element instanceof UiContainer) {
                if (((UiContainer) element).mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)) {
                    return true;
                }
            }
            // Fire Event when element is draggable
            if (element instanceof Draggable && ((Draggable) element).isClicked()) {
                if (((Draggable) element).onDrag(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)) {
                    return true;
                }
            }
        }
        // Check the container itself
        if (this instanceof Draggable && ((Draggable) this).isClicked()) {
            if (((Draggable) this).onDrag(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the forwarding of the mouse click events to the right elements.
     */
    public boolean mouseReleased(int mouseX, int mouseY, int state) {
        // Check the containing ui elements
        for (UiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UiContainer
            if (element instanceof UiContainer) {
                if (((UiContainer) element).mouseReleased(mouseX, mouseY, state)) {
                    return true;
                }
            }

            // Fire Release-Event
            if (element instanceof Draggable && ((Draggable) element).isClicked()) {
                if (((Draggable) element).onRelease(mouseX, mouseY, state)) {
                    return true;
                }
            }
        }
        // Check the container itself
        if (this instanceof Draggable && ((Draggable) this).isClicked()) {
            if (((Draggable) this).onRelease(mouseX, mouseY, state)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the forwarding of key events to the contained elements.
     */
    public void keyTyped(char typedChar, int keyCode) {
        // Check the containing ui elements
        for (UiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UiContainer
            if (element instanceof UiContainer) {
                this.keyTyped(typedChar, keyCode);
            }

            // Fire Release-Event
            if (element instanceof Typeable) {
                ((Typeable) element).onKeyTyped(typedChar, keyCode);
            }
        }
        // Check the container itself
        if (this instanceof Typeable) {
            ((Typeable) this).onKeyTyped(typedChar, keyCode);
        }
    }

    /**
     * Checks if the mouse position is in bounds of some element that has a tooltip. Then the tooltip should be drawn. Only one tooltip should
     * visible at all.
     *
     * @param mouseX
     * @param mouseY
     */
    public boolean handleTooltip(UiScreen screen, int mouseX, int mouseY) {
        // Check the containing ui elements
        for (UiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UiContainer
            if (element instanceof UiContainer) {
                if (this.handleTooltip(screen, mouseX, mouseY)) {
                    return true;
                }
            }

            // Draw Tooltip
            if (element.isMouseHovering(mouseX, mouseY) && element.getTooltip() != null) {
                screen.setCurrentTooltip(element.getTooltip());
                return true;
            }
        }
        // Check the container itself
        if (this.isMouseHovering(mouseX, mouseY) && this.getTooltip() != null) {
            screen.setCurrentTooltip(this);
            return true;
        }
        return false;
    }

    /**
     * @return List containing all elements in the reversed order as they are drawn.
     */
    private List<UiElement> getContainedElementsReversed() {
        List<UiElement> list = new ArrayList<>(this.containedElements);
        Collections.reverse(list);
        return list;
    }

}
