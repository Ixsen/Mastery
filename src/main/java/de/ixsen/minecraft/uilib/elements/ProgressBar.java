package de.ixsen.minecraft.uilib.elements;

import de.ixsen.minecraft.uilib.common.Orientation;
import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.uilib.elements.core.ScalableGuiElement;

public class ProgressBar extends ScalableGuiElement {

    private float min;
    private float max;
    private Orientation orientation;
    private ReadableColor color;

    public ProgressBar(Orientation orientation, ReadableColor color) {
        this(0, 100, orientation, color);
    }

    public ProgressBar(float min, float max, Orientation orientation, ReadableColor color) {
        this(min, max, orientation, color, 1f);
    }

    public ProgressBar(GuiContainer parentContainer, float min, float max, Orientation orientation,
                       ReadableColor color) {
        this(parentContainer, min, max, orientation, color, 1f);
    }

    public ProgressBar(float min, float max, Orientation orientation, ReadableColor color, float scale) {
        super(scale);
        this.min = min;
        this.max = max;
        this.orientation = orientation;
        this.color = color;
    }

    public ProgressBar(GuiContainer parentContainer, float min, float max, Orientation orientation,
                       ReadableColor color, float scale) {
        super(parentContainer, scale);
        this.min = min;
        this.max = max;
        this.orientation = orientation;
        this.color = color;
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (!this.isVisible()) {
            return;
        }

    }

    public float getMin() {
        return this.min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return this.max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public ReadableColor getColor() {
        return this.color;
    }

    public void setColor(ReadableColor color) {
        this.color = color;
    }
}
