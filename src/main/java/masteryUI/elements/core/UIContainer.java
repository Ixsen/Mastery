package masteryUI.elements.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import masteryUI.functions.Clickable;
import masteryUI.functions.Draggable;
import masteryUI.functions.Focusable;
import masteryUI.functions.Typeable;
import masteryUI.layout.HorizontalLayout;
import masteryUI.layout.LayoutData;
import masteryUI.layout.UILayout;

/**
 * @author Subaro
 */
public abstract class UIContainer extends UIElement {

    /** The default layout used by ui containers. */
    private static UILayout DEFAULT_LAYOUT = new HorizontalLayout();
    /** List containg all the ui elemnts that are assigned to this container. */
    private final HashMap<UIElement, LayoutData> containedElements;
    /** The layout determines the position of the children ui elements */
    private UILayout layout;

    /**
     * Constructor for the root container element.
     *
     * @param screen Screen to pass.
     */
    public UIContainer(UIMCScreen screen) {
        super();
        this.screen = screen;
        this.containedElements = new LinkedHashMap<>();
        this.setLayout(DEFAULT_LAYOUT);
    }

    public UIContainer() {
        super();
        this.containedElements = new LinkedHashMap<>();
        this.setLayout(DEFAULT_LAYOUT);
    }

    public UIContainer(UIContainer parentContainer) {
        super(parentContainer);
        this.containedElements = new LinkedHashMap<>();
        this.setLayout(DEFAULT_LAYOUT);
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        // Call super to draw background if wanted
        super.draw(parentX, parentY, mouseX, mouseY, partialTicks);

        // Draw all other elements
        Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
        for (UIElement element : this.containedElements.keySet()) {
            if (element.isVisible()) {
                // Only draw if visible
                GL11.glPushMatrix();
                element.draw(myGlobalPos.getX(), myGlobalPos.getY(), mouseX, mouseY, partialTicks);
                GL11.glPopMatrix();
            }
        }
    }

    /**
     * Delegates the gui call to every component
     */
    public void initGui() {
        for (UIElement element : this.containedElements.keySet()) {
            if (element instanceof UIContainer) {
                ((UIContainer) element).initGui();
            }
        }
    }

    public UILayout getLayout() {
        return this.layout;
    }

    public void setLayout(UILayout layout) {
        this.layout = layout;
    }

    /**
     * Add one or more child to this container with the same default layout data of the set layout.
     *
     * @param elements UIElements to add.
     */
    public void addElement(UIElement... elements) {
        for (UIElement uiElement : elements) {
            this.containedElements.put(uiElement, this.getLayout().getDefaultData());
            uiElement.screen = this.screen;
        }
    }

    /**
     * Add one or more child to this container with the same layout data.
     *
     * @param data LayoutData to set.
     * @param elements UIElements to add.
     */
    public void addElement(LayoutData data, UIElement... elements) {
        for (UIElement uiElement : elements) {
            this.containedElements.put(uiElement, data);
            uiElement.screen = this.screen;
        }
    }

    /**
     * Removes the child for this container.
     *
     * @param element UIElement to remove.
     */
    public void removeElement(UIElement element) {
        this.containedElements.remove(element);
    }

    /**
     * Changes the layout data of a certain ui element.
     *
     * @param data New LayoutData to set.
     * @param element UIElement to change.
     */
    public void changeLayoutData(LayoutData data, UIElement element) {
        if (this.containedElements.get(element) != null) {
            this.containedElements.replace(element, data);
        }
    }

    /**
     * Layouts all elements and sets their position.
     */
    protected void layoutElements() {
        this.getLayout().layoutElements(this.containedElements);
        this.setSize(this.getLayout().calculateMinimumSize(this.containedElements));
    }

    /**
     * Handles the forwarding of the mouse click events to the right elements.
     */
    protected boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        // Check the containing ui elements
        for (UIElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UIContainer
            if (element instanceof UIContainer) {
                if (((UIContainer) element).mouseClicked(mouseX, mouseY, mouseButton)) {
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
    protected boolean processFocus(int mouseX, int mouseY, int mouseButton) throws IOException {
        // Only focus when left-clicking
        if (mouseButton != 0) {
            return true;
        }
        // Check the containing ui elements
        for (UIElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UIContainer
            if (element instanceof UIContainer) {
                if (((UIContainer) element).processFocus(mouseX, mouseY, mouseButton)) {
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
    protected boolean mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        // Check the containing ui elements
        for (UIElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UIContainer
            if (element instanceof UIContainer) {
                if (((UIContainer) element).mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)) {
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
    protected boolean mouseReleased(int mouseX, int mouseY, int state) {
        // Check the containing ui elements
        for (UIElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UIContainer
            if (element instanceof UIContainer) {
                if (((UIContainer) element).mouseReleased(mouseX, mouseY, state)) {
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
    protected void keyTyped(char typedChar, int keyCode) {
        // Check the containing ui elements
        for (UIElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UIContainer
            if (element instanceof UIContainer) {
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
    protected boolean handleTooltip(UIMCScreen screen, int mouseX, int mouseY) {
        // Check the containing ui elements
        for (UIElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an UIContainer
            if (element instanceof UIContainer) {
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
    private List<UIElement> getContainedElementsReversed() {
        List<UIElement> list = new ArrayList<>(this.containedElements.keySet());
        Collections.reverse(list);
        return list;
    }
}