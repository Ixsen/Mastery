package de.ixsen.minecraft.uilib.elements.container;

import java.io.IOException;
import java.util.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.core.GuiElement;
import de.ixsen.minecraft.uilib.elements.core.MasteryGuiScreen;
import de.ixsen.minecraft.uilib.functions.Clickable;
import de.ixsen.minecraft.uilib.functions.Draggable;
import de.ixsen.minecraft.uilib.functions.Focusable;
import de.ixsen.minecraft.uilib.functions.Typeable;
import de.ixsen.minecraft.uilib.layout.GuiLayout;
import de.ixsen.minecraft.uilib.layout.HorizontalLayout;
import de.ixsen.minecraft.uilib.layout.LayoutData;

/**
 * @author Subaro
 */
public class GuiContainer extends GuiElement {

    /** The default layout used by ui containers. */
    private static GuiLayout DEFAULT_LAYOUT = new HorizontalLayout();
    /** List containg all the ui elemnts that are assigned to this container. */
    protected final HashMap<GuiElement, LayoutData> containedElements;
    /** The layout determines the position of the children ui elements */
    private GuiLayout layout;

    /**
     * Constructor for the root container element.
     *
     * @param screen
     *            Screen to pass.
     */
    public GuiContainer(MasteryGuiScreen screen) {
        super();
        this.screen = screen;
        this.containedElements = new LinkedHashMap<>();
        this.setLayout(GuiContainer.DEFAULT_LAYOUT);
    }

    public GuiContainer() {
        super();
        this.containedElements = new LinkedHashMap<>();
        this.setLayout(GuiContainer.DEFAULT_LAYOUT);
    }

    public GuiContainer(GuiContainer parentContainer) {
        super(parentContainer);
        this.containedElements = new LinkedHashMap<>();
        this.setLayout(GuiContainer.DEFAULT_LAYOUT);
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        // Draw all other elements
        Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
        for (GuiElement element : this.containedElements.keySet()) {
            if (element.isVisible()) {
                // Only draw if visible
                GL11.glPushMatrix();
                element.draw(myGlobalPos.getX(), myGlobalPos.getY(), mouseX, mouseY, partialTicks);
                GL11.glColor4f(1f, 1f, 1f, 1f);
                GL11.glPopMatrix();
            }
        }
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        // Has no foreground
    }

    /**
     * Delegates the gui call to every component
     */
    public void initGui() {
        for (GuiElement element : this.containedElements.keySet()) {
            if (element instanceof GuiContainer) {
                ((GuiContainer) element).initGui();
            }
        }
    }

    public GuiLayout getLayout() {
        return this.layout;
    }

    public void setLayout(GuiLayout layout) {
        this.layout = layout;
    }

    /**
     * Add one or more child to this container with the same default layout data of the set layout.
     *
     * @param elements
     *            UIElements to add.
     */
    public void addElement(GuiElement... elements) {
        for (GuiElement element : elements) {
            this.containedElements.put(element, this.getLayout().getDefaultData());
            element.setScreen(this.screen);
        }
        this.setSize(this.getSize());
    }

    /**
     * Add one or more child to this container with the same layout data.
     *
     * @param data
     *            LayoutData to set.
     * @param elements
     *            UIElements to add.
     */
    public void addElement(LayoutData data, GuiElement... elements) {
        for (GuiElement element : elements) {
            this.containedElements.put(element, data);
            element.setScreen(this.screen);
        }
    }

    /**
     * Removes the child for this container.
     *
     * @param element
     *            GuiElement to remove.
     */
    public void removeElement(GuiElement element) {
        this.containedElements.remove(element);
    }

    /**
     * Changes the layout data of a certain ui element.
     *
     * @param data
     *            New LayoutData to set.
     * @param element
     *            GuiElement to change.
     */
    public void changeLayoutData(LayoutData data, GuiElement element) {
        if (this.containedElements.get(element) != null) {
            this.containedElements.replace(element, data);
        }
    }

    /**
     * Layouts all elements and sets their position.
     */
    public void layoutElements() {
        this.getLayout().layoutElements(this.containedElements);
        this.setSize(this.getLayout().calculateMinimumSize(this.containedElements));
    }

    /**
     * Handles the forwarding of the mouse click events to the right elements.
     */
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        // Check the containing ui elements
        for (GuiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an GuiContainer
            if (element instanceof GuiContainer) {
                if (((GuiContainer) element).mouseClicked(mouseX, mouseY, mouseButton)) {
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
    public boolean processFocus(int mouseX, int mouseY, int mouseButton) throws IOException {
        // Only focus when left-clicking
        if (mouseButton != 0) {
            return true;
        }
        // Check the containing ui elements
        for (GuiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an GuiContainer
            if (element instanceof GuiContainer) {
                if (((GuiContainer) element).processFocus(mouseX, mouseY, mouseButton)) {
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
        for (GuiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an GuiContainer
            if (element instanceof GuiContainer) {
                if (((GuiContainer) element).mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)) {
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
        for (GuiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an GuiContainer
            if (element instanceof GuiContainer) {
                if (((GuiContainer) element).mouseReleased(mouseX, mouseY, state)) {
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
        for (GuiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an GuiContainer
            if (element instanceof GuiContainer) {
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
    public boolean handleTooltip(MasteryGuiScreen screen, int mouseX, int mouseY) {
        // Check the containing ui elements
        for (GuiElement element : this.getContainedElementsReversed()) {
            if (!element.isVisible() || !element.isEnabled()) {
                continue;
            }
            // Forward when the element is an GuiContainer
            if (element instanceof GuiContainer) {
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
    private List<GuiElement> getContainedElementsReversed() {
        List<GuiElement> list = new ArrayList<>(this.containedElements.keySet());
        Collections.reverse(list);
        return list;
    }

    @Override
    public ReadableDimension getSize() {
        int width = 0;
        int height = 0;

        for (Map.Entry<GuiElement, LayoutData> guiElementLayoutDataEntry : this.containedElements.entrySet()) {
            width += guiElementLayoutDataEntry.getKey().getSize().getWidth();
            height += guiElementLayoutDataEntry.getKey().getSize().getHeight();

        }

        return new Dimension(width, height);
    }

}
