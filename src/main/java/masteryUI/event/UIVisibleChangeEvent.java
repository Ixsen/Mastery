package masteryUI.event;

import masteryUI.elements.core.UIElement;

/**
 * @author Subaro
 */
public class UIVisibleChangeEvent extends UIEvent {

    private boolean oldValue;
    private boolean newValue;

    public UIVisibleChangeEvent(UIElement actuatorElement, boolean oldValue, boolean newValue) {
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
