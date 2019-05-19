package de.ixsen.minecraft.uilib.event;

import de.ixsen.minecraft.uilib.elements.core.UIElement;

/**
 * @author Subaro
 */
public class UIValueChangeEvent<T> extends UIEvent {

    private T newValue;

    public UIValueChangeEvent(UIElement actuatorElement, T newValue) {
        super(actuatorElement);
        this.newValue = newValue;
    }

    public T getNewValue() {
        return this.newValue;
    }
}
