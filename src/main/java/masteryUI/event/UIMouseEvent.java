package masteryUI.event;

import org.lwjgl.util.Point;

import masteryUI.elements.core.UIElement;

/**
 * @author Subaro
 */
public class UIMouseEvent extends UIEvent {
    private Point mousePosition;
    private int mouseButton;

    public UIMouseEvent(UIElement actuatorElement, Point mousePosition, int mouseButton) {
        super(actuatorElement);
        this.mousePosition = mousePosition;
        this.mouseButton = mouseButton;
    }

    public Point getMousePosition() {
        return this.mousePosition;
    }

    public int getMouseButton() {
        return this.mouseButton;
    }
}
