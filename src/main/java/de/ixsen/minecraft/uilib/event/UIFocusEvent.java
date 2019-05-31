package de.ixsen.minecraft.uilib.event;

import de.ixsen.minecraft.uilib.elements.core.GuiElement;

public class UIFocusEvent extends UIEvent {

    private boolean oldValue;
    private boolean newValue;

    public UIFocusEvent(GuiElement actuatorElement, boolean oldValue, boolean newValue) {
        super(actuatorElement);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public boolean isOldValue() {
        return this.oldValue;
    }

    public boolean isNewValue() {
        return this.newValue;
    }
}
