package de.ixsen.minecraft.uilib.event;

import de.ixsen.minecraft.uilib.elements.core.UIElement;

/**
 * @author Subaro
 */
public class UIEvent {
    private UIElement actuatorElement;

    public UIEvent(UIElement actuatorElement) {
        this.actuatorElement = actuatorElement;
    }

    public UIElement getActuatorElement() {
        return this.actuatorElement;
    }
}
