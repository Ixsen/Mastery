package de.ixsen.minecraft.uilib.layout;

import java.util.HashMap;

import de.ixsen.minecraft.uilib.elements.core.GuiElement;
import org.lwjgl.util.ReadableDimension;

/**
 * Interfaces that provides the methods for layouts.
 *
 * @author Subaro
 */
public interface GuiLayout {

    /**
     * Call this method to layout all elements. The position of the UI elements can be changed afterward and are only reset after calling this
     * method again.
     *
     * @param elementData The data of the elements to layout.
     */
    void layoutElements(HashMap<GuiElement, LayoutData> elementData);

    /**
     * Calculates the minimum size of the container that refers to the specific layout.
     *
     * @return ReadableDimension containing the minimum size.
     */
    ReadableDimension calculateMinimumSize(HashMap<GuiElement, LayoutData> elementData);

    /**
     * Returns the default layout data for this specific layout.
     *
     * @return LayoutData that is used for unspecified objects.
     */
    LayoutData getDefaultData();
}
