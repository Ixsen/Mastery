package de.ixsen.minecraft.uilib.event;

import org.lwjgl.util.Point;

import de.ixsen.minecraft.uilib.elements.core.UiElement;

/**
 * @author Subaro
 */
public class UIMouseEvent extends UIEvent {
    private Point mousePosition;
    private int mouseButton;
    private long timeSinceLastCall = 0;
    private int state = 0;

    public UIMouseEvent(UiElement actuatorElement, Point mousePosition, int mouseButton) {
        super(actuatorElement);
        this.mousePosition = mousePosition;
        this.mouseButton = mouseButton;
        this.timeSinceLastCall = 0;
    }

    public UIMouseEvent(UiElement actuatorElement, Point mousePosition, int mouseButton, int state) {
        super(actuatorElement);
        this.mousePosition = mousePosition;
        this.mouseButton = mouseButton;
        this.state = state;
    }

    public UIMouseEvent(UiElement actuatorElement, Point mousePosition, int mouseButton, long timeSinceLastCall) {
        super(actuatorElement);
        this.mousePosition = mousePosition;
        this.mouseButton = mouseButton;
        this.timeSinceLastCall = timeSinceLastCall;
    }

    public Point getMousePosition() {
        return this.mousePosition;
    }

    public int getMouseButton() {
        return this.mouseButton;
    }

    public long getTimeSinceLastCall() {
        return this.timeSinceLastCall;
    }

    public int getState() {
        return this.state;
    }
}
