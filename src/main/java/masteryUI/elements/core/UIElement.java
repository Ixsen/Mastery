package masteryUI.elements.core;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import masteryUI.colors.UIColors;
import masteryUI.event.UIEnableChangeEvent;
import masteryUI.event.UIEvent;
import masteryUI.event.UIEventRunnable;
import masteryUI.event.UIVisibleChangeEvent;
import masteryUI.functions.UIListenerType;
import net.minecraft.client.Minecraft;

/**
 * The basic element for every UI element. Every other element need to subclass this element. It actually introduces different kind of
 * functionality to every element. At first the visibility of an element. The visibility allows a developer to decide when an element should
 * be drawn to the screen. Second the enabled state. The enabled state determines whether you can interact with an element throught
 * listeners.
 *
 * @author Subaro
 */
public abstract class UIElement extends UIGuiWrapper {

    /** Contains the list for every registered listener for this ui element. */
    protected HashMap<Class, List<Consumer<? extends UIEvent>>> listeners;
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
    /** Reference to the current Minecraft instance. */
    private Minecraft minecraft;
    /** Tooltip element for this element. */
    private UIElement tooltip;
    /** The color of the background of the element */
    private ReadableColor backgroundColor;

    public UIElement() {
        this.listeners = new HashMap<>();
        this.visible = true;
        this.enabled = true;
        this.minimumSize = new Dimension(0, 0);
        this.maximumSize = new Dimension(0, 0);
        this.position = new Point(0, 0);
        this.minecraft = Minecraft.getMinecraft();
    }

    public UIElement(UIContainer parentContainer) {
        this();
        this.parentContainer = parentContainer;
    }

    /** Method used to draw the current element to the screen. By default it will only draw the background as a solid colored rectangle. */
    public void draw(int mouseX, int mouseY, float partialTicks) {
        if (this.backgroundColor != null) {
            Point globalPos = this.getGlobalPosition();
            drawRect(globalPos.getX(), globalPos.getY(), this.getMinimumSize().getWidth(),
                    this.getMinimumSize().getHeight(), UIColors.toInt(this.backgroundColor));
        }
    }

    /**
     * @return True if the element is visible, false otherwise
     */
    public boolean isVisible() {
        return this.parentContainer != null ? this.visible && this.parentContainer.isVisible() : this.visible;
    }

    /**
     * @param visible Determines the visiblility
     */
    public void setVisible(boolean visible) {
        // Check if visibility toggled
        if (this.visible != visible) {
            this.visible = visible;
            // Notify every listener
            UIEvent event = new UIVisibleChangeEvent(this, !visible, visible);
            for (Consumer<? extends UIEvent> listener : this.listeners.get(event.getClass())) {
                event.getClass().accept(event);
            }
        }
    }

    /**
     * Adds a new visibility change listener to the object. The runnable is executed every time the visibiliy value acually changes.
     *
     * @param <E>
     * @param onVisibleChange UIRunnable to run.
     */
    public void addVisibilityChangeListener(Consumer<UIVisibleChangeEvent> onVisibleChange) {
        if (onVisibleChange != null) {
            Class<?> wow = UIVisibleChangeEvent.class;
            this.listeners.get(wow).add(onVisibleChange);
        }
    }

    /**
     * @return True if the element is 'enabled', false otherwise
     */
    public boolean isEnabled() {
        return this.parentContainer != null ? this.parentContainer.isEnabled() && this.enabled : this.enabled;
    }

    /**
     * @param enabled Value for the new state
     */
    public void setEnabled(boolean enabled) {
        // Check if enabled state toggled
        if (this.enabled != enabled) {
            this.enabled = enabled;
            // Notify every listener
            UIEnableChangeEvent event = new UIEnableChangeEvent(this, !enabled, enabled);
            for (UIEventRunnable<UIEnableChangeEvent> listener : this.onEnableListeners) {
                listener.run(event);
            }
        }
    }

    /**
     * Adds a new enabled change listener to the object. The runnable is executed every time the enabled state value acually changes.
     *
     * @param onEnabledChange UIRunnable to run.
     */
    public void addEnabledChangeListener(UIEventRunnable<UIEnableChangeEvent> onEnabledChange) {
        if (onEnabledChange != null) {
            this.listeners.get(UIListenerType.ON_ENABLE_CHANGED).add(onEnabledChange);
        }
    }

    /**
     * Should be overitten by every subclassed UI element.
     *
     * @return The minimum size for this element. Used by the layout.
     */
    public ReadableDimension getMinimumSize() {
        return this.minimumSize;
    }

    /**
     * @param minimumSize The minimum size for this element.
     */
    public void setMinimumSize(ReadableDimension minimumSize) {
        this.minimumSize = minimumSize;
    }

    /**
     * Should be overitten by every subclassed UIElement.
     *
     * @return The maximum size of this element.
     */
    public ReadableDimension getMaximumSize() {
        return this.maximumSize;
    }

    /**
     * @param maximumSize The maximum size of the element to set.
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
     * @return The reference to Minecraft.
     */
    public Minecraft getMinecraft() {
        return this.minecraft;
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
     * @param tooltip The ui element that should be drawn as the tooltip.
     */
    public void setTooltip(UIElement tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @return The current global position of the element.
     */
    public Point getGlobalPosition() {
        if (this.parentContainer != null) {
            Point parentPos = this.parentContainer.getGlobalPosition();
            return new Point(parentPos.getX() + this.position.getX(), parentPos.getY() + this.position.getY());
        } else {
            return this.position;
        }
    }

    /**
     * @return The current relative position of the element to it's parent.
     */
    public Point getRelativePosition() {
        return this.position;
    }

    /**
     * CAREFUL do not use this method when using a layout. Will override the position determined by the layout.
     *
     * @param position The relative position to set.
     */
    public void setPosition(Point position) {
        this.position = position;
    }
}
