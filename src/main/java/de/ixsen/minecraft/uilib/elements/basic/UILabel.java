package de.ixsen.minecraft.uilib.elements.basic;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.colors.UIColors;
import de.ixsen.minecraft.uilib.elements.core.UIClickableElement;
import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import net.minecraft.client.gui.FontRenderer;

/**
 * @author Subaro
 */
public class UILabel extends UIClickableElement {

    /**
     * Determines the alignment of the ui label.
     */
    public enum UIAlignment {
        TOP_LEFT, TOP_CENTER, TOP_RIGHT, MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT, BOT_LEFT, BOT_CENTER, BOT_RIGHT
    }

    /** Text to show */
    private String text;
    /** Color for the text element */
    private ReadableColor textColor;
    /** Alignment for this lable */
    private UIAlignment alignment;

    public UILabel(String text, ReadableColor textColor, float scale, UIAlignment alignment) {
        super(scale);
        this.text = text;
        this.textColor = textColor;
        this.alignment = alignment;
    }

    public UILabel(UIContainer parentContainer, String text, ReadableColor textColor, float scale,
            UIAlignment alignment) {
        super(parentContainer, scale);
        this.text = text;
        this.textColor = textColor;
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        {
            Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
            // Draw background
            super.draw(parentX, parentY, mouseX, mouseY, partialTicks);

            // Draw 'label'
            FontRenderer fontRenderer = this.mc.fontRenderer;
            ReadableDimension calculatedSize = new Dimension(fontRenderer.getStringWidth(this.text),
                    fontRenderer.FONT_HEIGHT);
            int y, x;

            switch (this.alignment) {
            case BOT_RIGHT:
                x = this.getMinimumSize().getWidth() - calculatedSize.getWidth();
                y = this.getMinimumSize().getHeight() - fontRenderer.FONT_HEIGHT;
                break;
            case BOT_CENTER:
                x = this.getMinimumSize().getWidth() / 2 - calculatedSize.getWidth() / 2;
                y = this.getMinimumSize().getHeight() - fontRenderer.FONT_HEIGHT;
                break;
            case BOT_LEFT:
                x = 0;
                y = this.getMinimumSize().getHeight() - fontRenderer.FONT_HEIGHT;
                break;
            case MIDDLE_RIGHT:
                x = this.getMinimumSize().getWidth() - calculatedSize.getWidth();
                y = this.getMinimumSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
                break;
            case MIDDLE_CENTER:
                x = this.getMinimumSize().getWidth() / 2 - calculatedSize.getWidth() / 2;
                y = this.getMinimumSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
                break;
            case MIDDLE_LEFT:
                x = 0;
                y = this.getMinimumSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
                break;
            case TOP_RIGHT:
                x = this.getMinimumSize().getWidth() - calculatedSize.getWidth();
                y = 0;
                break;
            case TOP_CENTER:
                x = this.getMinimumSize().getWidth() / 2 - calculatedSize.getWidth() / 2;
                y = 0;
                break;
            case TOP_LEFT:
            default:
                x = 0;
                y = 0;
                break;
            }

            Point labelPos = new Point(myGlobalPos.getX() + x, myGlobalPos.getY() + y);
            this.drawString(this.mc.fontRenderer, this.text, labelPos.getX(), labelPos.getY(),
                    UIColors.toInt(this.textColor));
        }
        this.endScaling();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ReadableColor getTextColor() {
        return this.textColor;
    }

    public void setTextColor(ReadableColor textColor) {
        this.textColor = textColor;
    }

    public UIAlignment getAlignment() {
        return this.alignment;
    }

    public void setAlignment(UIAlignment alignment) {
        this.alignment = alignment;
    }
}
