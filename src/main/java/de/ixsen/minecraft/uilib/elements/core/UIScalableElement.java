package de.ixsen.minecraft.uilib.elements.core;

import org.lwjgl.util.Point;

/**
 * An element that can be scaled.
 *
 * @author Subaro
 */
public abstract class UIScalableElement extends UIElement {

    private float scale = 1;

    public UIScalableElement(float scale) {
        super();
        this.scale = scale;
    }

    public UIScalableElement(UIContainer parentContainer, float scale) {
        super(parentContainer);
        this.scale = scale;
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(new Point((int) (position.getX() * 1 / this.getScale()),
                (int) (position.getY() * 1 / this.getScale())));
    }

    /**
     * @param mouseX X Position of the mouse
     * @param mouseY Y Position of the mouse
     * @return true, if the mouse position is inside the element's bounds. Works for scaled elements
     */
    @Override
    public boolean isMouseHovering(int mouseX, int mouseY) {
        Point gPos = this.getGlobalPosition();
        int minX = (int) (this.getScale() * gPos.getX());
        int maxX = (int) (this.getScale() * (gPos.getX() + this.getMinimumSize().getWidth()));
        int minY = (int) (this.getScale() * gPos.getY());
        int maxY = (int) (this.getScale() * (gPos.getY() + this.getMinimumSize().getHeight()));
        if (mouseX >= minX && mouseX <= maxX && mouseY >= minY && mouseY <= maxY) {
            return true;
        }
        return false;
    }

    /**
     * @param scale the scale to set
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * @return the scale
     */
    public float getScale() {
        return this.scale;
    }
}
