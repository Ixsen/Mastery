package de.ixsen.minecraft.uilib.elements.core;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;

/**
 * An element that can be scaled.
 *
 * @author Subaro
 */
public abstract class ScalableUiElement extends UiElement {

    private float scale;

    public ScalableUiElement(float scale) {
        super();
        this.scale = scale;
    }

    public ScalableUiElement(UiContainer parentContainer, float scale) {
        super(parentContainer);
        this.scale = scale;
    }

    @Override
    public void setRelativePosition(Point relativePosition) {
        super.setRelativePosition(new Point((int) (relativePosition.getX() * 1 / this.getScale()),
                (int) (relativePosition.getY() * 1 / this.getScale())));
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        super.draw(parentX, parentY, mouseX, mouseY, partialTicks);
        this.endScaling();
    }

    /**
     * @param mouseX
     *            X Position of the mouse
     * @param mouseY
     *            Y Position of the mouse
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
     * @param scale
     *            the scale to set
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

    @Override
    public ReadableDimension getMinimumSize() {
//        return super.getMinimumSize();
        ReadableDimension minimumSize = super.getMinimumSize();
        return new Dimension((int) (minimumSize.getWidth() * this.getScale()),
                (int) (minimumSize.getHeight() * this.getScale()));
    }
}
