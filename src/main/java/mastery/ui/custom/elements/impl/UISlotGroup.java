package mastery.ui.custom.elements.impl;

import java.util.ArrayList;
import java.util.List;

import mastery.ui.custom.elements.interfaces.IUISlot;
import mastery.ui.custom.elements.interfaces.IUISlotTabGroup;
import mastery.ui.custom.functions.Activatable;

/**
 * @author Subaro
 */
public class UISlotGroup implements IUISlotTabGroup {

    List<IUISlot> registeredSlots;

    public UISlotGroup() {
        this.registeredSlots = new ArrayList<>();
    }

    @Override
    public void synchUISlots(IUISlot actuator) {
        for (IUISlot iuiSlot : this.registeredSlots) {
            if (iuiSlot != actuator && iuiSlot instanceof Activatable) {
                ((Activatable) iuiSlot).setActive(false);
            }
        }
    }

    @Override
    public void addSlot(IUISlot slot) {
        if (!this.registeredSlots.contains(slot)) {
            this.registeredSlots.add(slot);
        }
    }

}
