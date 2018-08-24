package mastery.ui.custom.elements.impl;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.element.AbstractGuiElement;

/**
 * @author Subaro
 */
public class UILayoutExtender extends AbstractGuiElement<UILayoutExtender> {

    @Override
    protected UILayoutExtender getThis() {
        return this;
    }

    @Override
    protected ReadableDimension calcMinSize() {
        return new Dimension(0, 0);
    }

}
