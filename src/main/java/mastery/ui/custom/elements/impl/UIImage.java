package mastery.ui.custom.elements.impl;
/**
 * @author Subaro
 */

import org.lwjgl.util.Point;

import mastery.ui.custom.elements.abstracts.AbstractUIImage;

public class UIImage extends AbstractUIImage<UIImage> {

    @Override
    protected UIImage getThis() {
        return this;
    }

    public UIImage setTextureSize(Point point) {
        return super.setTextureSize(point.getX(), point.getY());
    }

    public UIImage setUV(Point point) {
        return super.setUV(point.getX(), point.getY());
    }

    public UIImage setUVSize(Point point) {
        return super.setUVSize(point.getX(), point.getY());
    }
}
