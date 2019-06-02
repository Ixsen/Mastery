package de.ixsen.minecraft.uilib.elements.container;

import org.lwjgl.util.Point;

import de.ixsen.minecraft.uilib.elements.core.ScalableUiElement;
import de.ixsen.minecraft.uilib.elements.core.UiElement;

public class HorizontalContainer extends UiContainer {

    private int paddingLeft = 0;
    private int paddingRight = 0;
    private int paddingTop = 0;
    private int paddingBot = 0;
    private int spacing = 0;

    public HorizontalContainer() {

    }

    public HorizontalContainer(int paddingLeft, int paddingRight, int paddingTop, int paddingBot, int spacing) {
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingTop = paddingTop;
        this.paddingBot = paddingBot;
        this.spacing = spacing;
    }

    @Override
    public void layoutElements() {
        int currentX = this.paddingLeft;
        int currentY = this.paddingTop;
        int maxHeight = 0;

        for (UiElement element : this.containedElements) {
            if (element instanceof UiContainer) {
                ((UiContainer) element).layoutElements();
            }

            currentX = currentX == this.paddingLeft ? currentX : currentX + this.spacing;

            float scaleCounter = element instanceof ScalableUiElement
                    ? (int) (1 / ((ScalableUiElement) element).getScale())
                    : 1;

            element.setRelativePosition(new Point(currentX, currentY));

            Point elementGlobalPosition = new Point(
                    (int) ((this.getGlobalPosition().getX() * scaleCounter + element.getRelativePosition().getX())),
                    (int) ((this.getGlobalPosition().getY() * scaleCounter + element.getRelativePosition().getY())));

            element.setGlobalPosition(elementGlobalPosition);

            int elementHeight = element.getMinimumSize().getHeight();
            maxHeight = maxHeight > elementHeight ? maxHeight : elementHeight;

            currentX += element.getMinimumSize().getWidth();

        }

        int width = currentX + this.paddingRight;
        int height = this.paddingTop + maxHeight + this.paddingBot;

        this.setSize(width, height);
    }

    public int getPaddingLeft() {
        return this.paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return this.paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getPaddingTop() {
        return this.paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public int getPaddingBot() {
        return this.paddingBot;
    }

    public void setPaddingBot(int paddingBot) {
        this.paddingBot = paddingBot;
    }

    public int getSpacing() {
        return this.spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }
}
