package de.ixsen.minecraft.uilib.elements.core;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

/**
 * An element that can be scaled.
 *
 * @author Subaro
 */
public abstract class ScalableGuiElement extends GuiElement {

    private float scale;

    public ScalableGuiElement(float scale) {
        super();
        this.scale = scale;
    }

    public ScalableGuiElement(GuiContainer parentContainer, float scale) {
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
    public ReadableDimension getSize() {
        return this.getMinimumSize();
    }
}
