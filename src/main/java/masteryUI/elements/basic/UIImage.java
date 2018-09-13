package masteryUI.elements.basic;

import masteryUI.elements.core.UIContainer;
import masteryUI.elements.core.UIScalableElement;
import masteryUI.elements.core.data.UIImageData;

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
