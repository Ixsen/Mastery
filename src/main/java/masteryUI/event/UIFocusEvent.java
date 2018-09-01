package masteryUI.event;

import masteryUI.elements.core.UIElement;

public class UIFocusEvent extends UIEvent {

    private boolean oldValue;
    private boolean newValue;

    public UIFocusEvent(UIElement actuatorElement, boolean oldValue, boolean newValue) {
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
