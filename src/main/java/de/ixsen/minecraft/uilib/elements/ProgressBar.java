package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.uilib.common.ColorUtils;
import de.ixsen.minecraft.uilib.common.Orientation;
import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.ScalableUiElement;
import net.minecraft.client.gui.Gui;

public class ProgressBar extends ScalableUiElement {

    private float min;
    private float max;
    private float progress;

    private Orientation orientation;
    private ReadableColor color;

    public ProgressBar(Orientation orientation, ReadableColor color) {
        this(0, 100, orientation, color);
    }

    public ProgressBar(float min, float max, Orientation orientation, ReadableColor color) {
        this(min, max, orientation, color, 1f);
    }

    public ProgressBar(UiContainer parentContainer, float min, float max, Orientation orientation,
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

    public ProgressBar(UiContainer parentContainer, float min, float max, Orientation orientation, ReadableColor color,
            float scale) {
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

        float percentage = this.progress / (this.max - this.min);
        switch (this.orientation) {
        case HORIZONTAL:
            int progressWidth = (int) (percentage * this.getMinimumSize().getWidth());
            Gui.drawRect(this.getGlobalPosition().getX(), this.getGlobalPosition().getY(),
                    this.getGlobalPosition().getX() + progressWidth,
                    this.getGlobalPosition().getY() + this.getMinimumSize().getHeight(), ColorUtils.toInt(this.color));
            break;
        case VERTICAL:
            int progressHeight = (int) (percentage * this.getMinimumSize().getHeight());
            Gui.drawRect(this.getGlobalPosition().getX(), this.getGlobalPosition().getY(),
                    this.getGlobalPosition().getX() + this.getMinimumSize().getWidth(),
                    this.getGlobalPosition().getY() + progressHeight, ColorUtils.toInt(this.color));
            break;
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

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setProgressData(int min, int progress, int max) {
        if (min > progress || max < progress) {
            throw new IllegalArgumentException();
        }
        this.min = min;
        this.progress = progress;
        this.max = max;
    }

}
