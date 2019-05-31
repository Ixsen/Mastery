package de.ixsen.minecraft.uilib.event;

import de.ixsen.minecraft.uilib.elements.core.GuiElement;

/**
 * @author Subaro
 */
public class UIEvent {
    private GuiElement actuatorElement;

    public UIEvent(GuiElement actuatorElement) {
        this.actuatorElement = actuatorElement;
    }

    public GuiElement getActuatorElement() {
        return this.actuatorElement;
    }
}
