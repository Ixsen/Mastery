package de.ixsen.minecraft.uilib.elements.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.common.ColorUtils;
import de.ixsen.minecraft.uilib.common.DrawUtils;
import de.ixsen.minecraft.uilib.common.ImmutablePoint;
import de.ixsen.minecraft.uilib.elements.container.UiContainer;
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
public abstract class UiElement extends UiWrapper {

    /**
     * Contains the list for every visibility listener for this ui element.
     */
    protected List<Consumer<UIVisibleChangeEvent>> visibilityListener;
    /**
     * Contains the list for every enable listener for this ui element.
     */
    protected List<Consumer<UIEnableChangeEvent>> enabledListener;
    /**
     * Screen reference of this container
     */
    protected UiScreen screen;
    /**
     * Visible means that it will be draw and is interactable via listeners.
     */
    private boolean visible;
    /**
     * Enable splits the element into two different states. The enabled state can allows interaction via listeners. The disabled state is still
     * drawn but has no listener interactions.
     */
    private boolean enabled;
    /**
     * The parent container for this ui element.
     */
    private UiContainer parentContainer;
    /**
     * Determines the minimum size for an ui element.
     */
    private ReadableDimension minimumSize;
    /**
     * Determines the maximum size for an ui element.
     */
    private ReadableDimension maximumSize;
    /**
     * The current relative relativePosition of the element to its parent. Mostly determined by the current layout.
     */
    private ImmutablePoint relativePosition;

    private Point globalPosition;
    /**
     * Tooltip element for this element.
     */
    private UiElement tooltip;
    /**
     * The color of the background of the element
     */
    private ReadableColor backgroundColor;
    private ReadableColor borderColor;

    public UiElement() {
        super();
        this.visibilityListener = new ArrayList<>();
        this.enabledListener = new ArrayList<>();
        this.visible = true;
        this.enabled = true;
        this.minimumSize = new Dimension(0, 0);
        this.maximumSize = new Dimension(0, 0);
        this.relativePosition = new ImmutablePoint(0, 0);
        this.globalPosition = new Point(0, 0);
    }

    public UiElement(UiContainer parentContainer) {
        this();
        this.parentContainer = parentContainer;
        this.screen = parentContainer.screen;
        parentContainer.addElements(this);
        this.globalPosition = new Point(0, 0);

    }

    /**
     * Method used to draw the current element to the screen. By default it will only draw the background as a solid colored rectangle.
     */
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);
        this.drawBorder(parentX, parentY, mouseX, mouseY, partialTicks);
        this.drawForeground(parentX, parentY, mouseX, mouseY, partialTicks);
    }

    /**
     * Draws the background for the ui element. Commonly a colored rect.
     */
    public void drawBackground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.backgroundColor == null) {
            return;
        }

        Gui.drawRect(this.globalPosition.getX(), this.globalPosition.getY(),
                this.globalPosition.getX() + this.getMinimumSize().getWidth(),
                this.globalPosition.getY() + this.getMinimumSize().getHeight(), ColorUtils.toInt(this.backgroundColor));

    }

    private void drawBorder(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.borderColor == null) {
            return;
        }

        DrawUtils.drawBorder(this.globalPosition.getX(), this.globalPosition.getY(),
                this.globalPosition.getX() + this.getMinimumSize().getWidth(),
                this.globalPosition.getY() + this.getMinimumSize().getHeight(), ColorUtils.toInt(this.borderColor));

    }

    public abstract void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks);

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
     * @param minimumSize
     *            The minimum size for this element. Non scaled!
     */
    public void setMinimumSize(ReadableDimension minimumSize) {
        this.minimumSize = minimumSize;
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
     * Should be overitten by every subclassed UiElement.
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
    public UiContainer getParentContainer() {
        return this.parentContainer;
    }

    /**
     * @return The tooltip element is it exists, null otherwise.
     */
    public UiElement getTooltip() {
        return this.tooltip;
    }

    /**
     * Sets an ui element as a tooltip. The tooltip element is drawn when the assigned element is hovered, visible and enabled.
     *
     * @param tooltip
     *            The ui element that should be drawn as the tooltip.
     */
    public void setTooltip(UiElement tooltip) {
        this.tooltip = tooltip;
    }

    public Point getGlobalPosition() {
        return this.globalPosition;
    }

    public void setGlobalPosition(int x, int y) {
        this.globalPosition = new Point(x, y);
    }

    public void setGlobalPosition(Point globalPosition) {
        this.globalPosition = globalPosition;
    }

    /**
     * @return The current relative relativePosition of the element to it's parent.
     */
    public Point getRelativePosition() {
        return new Point(this.relativePosition.getX(), this.relativePosition.getY());
    }

    /**
     * CAREFUL do not use this method when using a layout. Will override the relativePosition determined by the layout. Sets the not-scaled
     * relativePosition;
     *
     * @param relativePosition
     *            The relative non-scaled relativePosition to set.
     */
    public void setRelativePosition(Point relativePosition) {
        this.relativePosition = new ImmutablePoint(relativePosition);
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
     * @return true, if the mouse relativePosition is inside the element's bounds. Works for scaled elements
     */
    public boolean isMouseHovering(int mouseX, int mouseY) {
        int minX = (int) (this.currentScaleFactor * this.globalPosition.getX());
        int maxX = (int) (this.currentScaleFactor * (this.globalPosition.getX() + this.getMinimumSize().getWidth()));
        int minY = (int) (this.currentScaleFactor * this.globalPosition.getY());
        int maxY = (int) (this.currentScaleFactor * (this.globalPosition.getY() + this.getMinimumSize().getHeight()));
        if (mouseX >= minX && mouseX <= maxX && mouseY >= minY && mouseY <= maxY) {
            return true;
        }
        return false;
    }

    public UiScreen getScreen() {
        return this.screen;
    }

    public void setScreen(UiScreen screen) {
        this.screen = screen;
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

    public ReadableColor getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(ReadableColor borderColor) {
        this.borderColor = borderColor;
    }
}
