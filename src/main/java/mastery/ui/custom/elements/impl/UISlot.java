package mastery.ui.custom.elements.impl;

import org.lwjgl.util.Point;

import lombok.Getter;
import lombok.Setter;
import mastery.ui.custom.elements.abstracts.AbstractUISlot;

/**
 * @author Subaro
 */
public class UISlot extends AbstractUISlot<UISlot> {

    @Getter
    @Setter
    private String id = "";

    public enum UIMainSlotTypes {
        SKILL_TREE, AUTHOR, SETTINGS, LORE
    }

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

    public UISlot setTextureSize(Point point) {
        return super.setTextureSize(point.getX(), point.getY());
    }

    public UISlot setUVDeactivated(Point point) {
        return super.setUV(point.getX(), point.getY());
    }

    public UISlot setUVActivated(Point point) {
        return super.setActiveUV(point.getX(), point.getY());
    }

    public UISlot setUVSize(Point point) {
        return super.setUVSize(point.getX(), point.getY());
    }
}
