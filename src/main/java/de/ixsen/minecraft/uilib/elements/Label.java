package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.uilib.common.Alignment;
import de.ixsen.minecraft.uilib.common.ColorUtils;
import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.ClickableUiElement;

/**
 * @author Subaro
 */
public class Label extends ClickableUiElement {

    /** Text to show */
    private String text;
    /** Color for the text element */
    private ReadableColor textColor;
    /** Alignment for this lable */
    private Alignment alignment;

    public Label(String text, ReadableColor textColor, float scale, Alignment alignment) {
        super(scale);
        this.text = text;
        this.textColor = textColor;
        this.alignment = alignment;

        this.calculateSize();
    }

    public Label(UiContainer parentContainer, String text, ReadableColor textColor, float scale) {
        super(parentContainer, scale);
        this.text = text;
        this.textColor = textColor;
        this.alignment = Alignment.MIDDLE_CENTER;

        this.calculateSize();
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (!this.text.equals("")) {
            // Draw 'label'

            float y, x;

            switch (this.alignment) {
            case BOT_RIGHT:
                x = this.getMaximumSize().getWidth() - this.getMinimumSize().getWidth();
                y = this.getMaximumSize().getHeight() - this.getFontHeight();
                break;
            case BOT_CENTER:
                x = this.getMaximumSize().getWidth() / 2 - this.getMinimumSize().getWidth() / 2;
                y = this.getMaximumSize().getHeight() - this.getFontHeight();
                break;
            case BOT_LEFT:
                x = 0;
                y = this.getMaximumSize().getHeight() - this.getFontHeight();
                break;
            case MIDDLE_RIGHT:
                x = this.getMaximumSize().getWidth() - this.getMinimumSize().getWidth();
                y = this.getMaximumSize().getHeight() / 2f - this.getFontHeight() / 2f;
                break;
            case MIDDLE_CENTER:
                x = this.getMaximumSize().getWidth() / 2f - this.getMinimumSize().getWidth() / 2f;
                y = this.getMaximumSize().getHeight() / 2f - this.getFontHeight() / 2f;
                break;
            case MIDDLE_LEFT:
                x = 0;
                y = this.getMaximumSize().getHeight() / 2f - this.getFontHeight() / 2f;
                break;
            case TOP_RIGHT:
                x = this.getMaximumSize().getWidth() - this.getMinimumSize().getWidth();
                y = 0;
                break;
            case TOP_CENTER:
                x = this.getMaximumSize().getWidth() / 2f - this.getMinimumSize().getWidth() / 2f;
                y = 0;
                break;
            case TOP_LEFT:
            default:
                x = 0;
                y = 0;
                break;
            }
            Point labelPos = new Point(this.getGlobalPosition().getX(), this.getGlobalPosition().getY());

            this.drawString(this.mc.fontRenderer, this.text, labelPos.getX(), labelPos.getY(),
                    ColorUtils.toInt(this.textColor));
        }

    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        this.calculateSize();
    }

    public ReadableColor getTextColor() {
        return this.textColor;
    }

    public void setTextColor(ReadableColor textColor) {
        this.textColor = textColor;
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void calculateSize() {
        this.setSize(new Dimension(this.mc.fontRenderer.getStringWidth(this.text), this.mc.fontRenderer.FONT_HEIGHT));
    }

    public float getFontHeight() {
        return (int) (this.mc.fontRenderer.FONT_HEIGHT * this.getScale());
    }
}
