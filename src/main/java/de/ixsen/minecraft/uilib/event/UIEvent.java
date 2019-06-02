package de.ixsen.minecraft.uilib.event;

import de.ixsen.minecraft.uilib.elements.core.UiElement;

/**
 * @author Subaro
 */
public class UIEvent {
    private UiElement actuatorElement;

    public UIEvent(UiElement actuatorElement) {
        this.actuatorElement = actuatorElement;
    }

    public UiElement getActuatorElement() {
        return this.actuatorElement;
    }
}
