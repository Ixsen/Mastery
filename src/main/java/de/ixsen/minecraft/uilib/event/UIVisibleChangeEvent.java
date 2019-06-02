package de.ixsen.minecraft.uilib.event;

import de.ixsen.minecraft.uilib.elements.core.UiElement;

/**
 * @author Subaro
 */
public class UIVisibleChangeEvent extends UIEvent {

    private boolean oldValue;
    private boolean newValue;

    public UIVisibleChangeEvent(UiElement actuatorElement, boolean oldValue, boolean newValue) {
        super(actuatorElement);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public boolean getOldValue() {
        return this.oldValue;
    }

    public boolean getNewValue() {
        return this.newValue;
    }
}
