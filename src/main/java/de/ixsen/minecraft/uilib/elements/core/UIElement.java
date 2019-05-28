package de.ixsen.minecraft.uilib.elements.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.colors.UIColors;
import de.ixsen.minecraft.uilib.event.UIEnableChangeEvent;
import de.ixsen.minecraft.uilib.event.UIVisibleChangeEvent;
import net.minecraft.client.gui.Gui;

/**
 * The basic element for every UI element. Every other element need to subclass this element. It actually introduces different kind of
 * functionality to every element. At first the visibility of an element. The visibility allows a developer to decide when an element should
 * be drawn to the screen. Second the enabled state. The enabled state determines whether you can interact with an element throught
 * listeners.
 *
 * @author Subaro
 */
public abstract class UIElement extends UIGuiWrapper {

    /** Contains the list for every visibility listener for this ui element. */
    protected List<Consumer<UIVisibleChangeEvent>> visibilityListener;
    /** Contains the list for every enable listener for this ui element. */
    protected List<Consumer<UIEnableChangeEvent>> enabledListener;

    /** Visible means that it will be draw and is interactable via listeners. */
    private boolean visible;
    /**
     * Enable splits the element into two different states. The enabled state can allows interaction via listeners. The disabled state is still
     * drawn but has no listener interactions.
     */
    private boolean enabled;
    /** The parent container for this ui element. */
    private UIContainer parentContainer;
    /** Determines the minimum size for an ui element. */
    private ReadableDimension minimumSize;
    /** Determines the maximum size for an ui element. */
    private ReadableDimension maximumSize;
    /** The current relative position of the element to its parent. Mostley determined by the current layout. */
    private Point position;
    /** Tooltip element for this element. */
    private UIElement tooltip;
    /** The color of the background of the element */
    private ReadableColor backgroundColor;
    /** Screen reference of this container */
    protected UIMCScreen screen;

    public UIElement() {
        super();
        this.visibilityListener = new ArrayList<>();
        this.enabledListener = new ArrayList<>();
        this.visible = true;
        this.enabled = true;
        this.minimumSize = new Dimension(0, 0);
        this.maximumSize = new Dimension(0, 0);
        this.position = new Point(0, 0);
    }

    public UIElement(UIContainer parentContainer) {
        this();
        this.parentContainer = parentContainer;
        this.screen = parentContainer.screen;
        parentContainer.addElement(this);
    }

    /** Method used to draw the current element to the screen. By default it will only draw the background as a solid colored rectangle. */
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);
    }

    /** Draws the background for the ui element. Commonly a colored rect. */
    public void drawBackground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        // Draw Background
        if (this.backgroundColor != null) {
            Point globalPos = this.getGlobalPosition(parentX, parentY);
            Gui.drawRect(globalPos.getX(), globalPos.getY(), globalPos.getX() + this.getMinimumSize().getWidth(),
                    globalPos.getY() + this.getMinimumSize().getHeight(), UIColors.toInt(this.backgroundColor));
        }
    }

    /**
     * @return True if the element is visible, false otherwise
     */
    public boolean isVisible() {
        return this.parentContainer != null ? this.visible && this.parentContainer.isVisible() : this.visible;
    }

    /**
     * @param visible
     *            Determines the visiblility
     */
    public void setVisible(boolean visible) {
        // Check if visibility toggled
        if (this.visible != visible) {
            this.visible = visible;
            // Notify every listener
            for (Consumer<UIVisibleChangeEvent> listener : this.visibilityListener) {
                listener.accept(new UIVisibleChangeEvent(this, !visible, visible));
            }
        }
    }

    /**
     * Adds a new visibility change listener to the object. The runnable is executed every time the visibiliy value acually changes.
     *
     * @param onVisibleChange
     *            UIRunnable to run.
     */
    public void addVisibilityChangeListener(Consumer<UIVisibleChangeEvent> onVisibleChange) {
        if (onVisibleChange != null) {
            this.visibilityListener.add(onVisibleChange);
        }
    }

    /**
     * @return True if the element is 'enabled', false otherwise
     */
    public boolean isEnabled() {
        return this.parentContainer != null ? this.parentContainer.isEnabled() && this.enabled : this.enabled;
    }

    /**
     * @param enabled
     *            Value for the new state
     */
    public void setEnabled(boolean enabled) {
        // Check if enabled state toggled
        if (this.enabled != enabled) {
            this.enabled = enabled;
            // Notify every listener
            for (Consumer<UIEnableChangeEvent> listener : this.enabledListener) {
                listener.accept(new UIEnableChangeEvent(this, !enabled, enabled));
            }
        }
    }

    /**
     * Adds a new enabled change listener to the object. The runnable is executed every time the enabled state value acually changes.
     *
     * @param onEnabledChange
     *            UIRunnable to run.
     */
    public void addEnabledChangeListener(Consumer<UIEnableChangeEvent> onEnabledChange) {
        if (onEnabledChange != null) {
            this.enabledListener.add(onEnabledChange);
        }
    }

    /**
     * Should be overitten by every subclassed UI element.
     *
     * @return The scaled minimum size for this element. Used by the layout.
     */
    public ReadableDimension getMinimumSize() {
        return this.minimumSize;
    }

    /**
     * Sets the size for this element.
     *
     * @param size
     *            Size to set. Non-Scaled!
     */
    public void setSize(ReadableDimension size) {
        this.minimumSize = size;
        this.maximumSize = size;
    }

    /**
     * Sets the size for this element.
     *
     * @param width
     *            Non-Scaled width
     * @param height
     *            Non-Scaled height
     */
    public void setSize(int width, int height) {
        this.minimumSize = new Dimension(width, height);
        this.maximumSize = new Dimension(width, height);
    }

    /**
     * @param minimumSize
     *            The minimum size for this element. Non scaled!
     */
    public void setMinimumSize(ReadableDimension minimumSize) {
        this.minimumSize = minimumSize;
    }

    /**
     * Should be overitten by every subclassed UIElement.
     *
     * @return The scaled maximum size of this element.
     */
    public ReadableDimension getMaximumSize() {
        return this.maximumSize;
    }

    /**
     * @param maximumSize
     *            The maximum size of the element to set. Non scaled!
     */
    public void setMaximumSize(ReadableDimension maximumSize) {
        this.maximumSize = maximumSize;
    }

    /**
     * @return The container that contains this element.
     */
    public UIContainer getParentContainer() {
        return this.parentContainer;
    }

    /**
     * @return The tooltip element is it exists, null otherwise.
     */
    public UIElement getTooltip() {
        return this.tooltip;
    }

    /**
     * Sets an ui element as a tooltip. The tooltip element is drawn when the assigned element is hovered, visible and enabled.
     *
     * @param tooltip
     *            The ui element that should be drawn as the tooltip.
     */
    public void setTooltip(UIElement tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @return The current global position of the element. If the element is scaled the position also correctly scaled.
     */
    public Point getGlobalPosition() {
        if (this.parentContainer != null) {
            Point parentPos = this.parentContainer.getGlobalPosition();
            return new Point(parentPos.getX() + this.position.getX(), parentPos.getY() + this.position.getY());
        } else {
            return new Point(this.position.getX(), this.position.getY());
        }
    }

    /**
     * @return The current global position of the element. If the element is scaled the position also correctly scaled.
     */
    public Point getGlobalPosition(int parentX, int parentY) {
        return new Point(parentX + this.getRelativePosition().getX(), parentY + this.getRelativePosition().getY());
    }

    /**
     * @return The current relative position of the element to it's parent.
     */
    public Point getRelativePosition() {
        return new Point(this.position.getX(), this.position.getY());
    }

    /**
     * CAREFUL do not use this method when using a layout. Will override the position determined by the layout. Sets the not-scaled position;
     *
     * @param position
     *            The relative non-scaled position to set.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @return The backgroundColor
     */
    public ReadableColor getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * @param backgroundColor
     *            The backgroundColor to set
     */
    public void setBackgroundColor(ReadableColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @param mouseX
     *            X Position of the mouse
     * @param mouseY
     *            Y Position of the mouse
     * @return true, if the mouse position is inside the element's bounds. Works for scaled elements
     */
    public boolean isMouseHovering(int mouseX, int mouseY) {
        Point gPos = this.getGlobalPosition();
        int minX = (int) (this.currentScaleFactor * gPos.getX());
        int maxX = (int) (this.currentScaleFactor * (gPos.getX() + this.getMinimumSize().getWidth()));
        int minY = (int) (this.currentScaleFactor * gPos.getY());
        int maxY = (int) (this.currentScaleFactor * (gPos.getY() + this.getMinimumSize().getHeight()));
        if (mouseX >= minX && mouseX <= maxX && mouseY >= minY && mouseY <= maxY) {
            return true;
        }
        return false;
    }
}
