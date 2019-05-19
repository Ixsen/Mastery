package de.ixsen.minecraft.uilib.elements.basic;

import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.elements.core.UIScalableElement;
import de.ixsen.minecraft.uilib.elements.core.data.UIImageData;

/**
 * @author Subaro
 */
public class UIImage extends UIScalableElement {

    /** The image that should be drawn into the button */
    protected UIImageData imageData;

    public UIImage(UIImageData imageData) {
        super(1);
        this.imageData = imageData;
    }

    public UIImage(UIContainer parentContainer, UIImageData imageData) {
        super(parentContainer, 1);
        this.imageData = imageData;
    }
}
