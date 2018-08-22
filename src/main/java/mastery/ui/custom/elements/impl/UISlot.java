package mastery.ui.custom.elements.impl;

import mastery.ui.custom.elements.abstracts.AbstractUISlot;

/**
 * @author Subaro
 */
public class UISlot extends AbstractUISlot<UISlot> {

    public UISlot(boolean active) {
        super(active);
    }

    public UISlot(boolean active, UISlotGroup group) {
        super(active, group);
    }

    @Override
    protected UISlot getThis() {
        return this;
    }

}
