package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.uilib.common.Alignment;
import de.ixsen.minecraft.uilib.common.ColorUtils;
import de.ixsen.minecraft.uilib.elements.core.ClickableGuiElement;
import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import net.minecraft.client.gui.FontRenderer;

/**
 * @author Subaro
 */
public class Label extends ClickableGuiElement {

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

    public Label(GuiContainer parentContainer, String text, ReadableColor textColor, float scale) {
        super(parentContainer, scale);
        this.text = text;
        this.textColor = textColor;
        this.alignment = Alignment.MIDDLE_CENTER;

        this.calculateSize();
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        Point myGlobalPos = this.getGlobalPosition(parentX, parentY);

        if (!this.text.equals("")) {
            // Draw 'label'
            FontRenderer fontRenderer = this.mc.fontRenderer;

            int y, x;

            switch (this.alignment) {
            case BOT_RIGHT:
                x = this.getMinimumSize().getWidth() - this.getMinimumSize().getWidth();
                y = this.getMinimumSize().getHeight() - fontRenderer.FONT_HEIGHT;
                break;
            case BOT_CENTER:
                x = this.getMinimumSize().getWidth() / 2 - this.getMinimumSize().getWidth() / 2;
                y = this.getMinimumSize().getHeight() - fontRenderer.FONT_HEIGHT;
                break;
            case BOT_LEFT:
                x = 0;
                y = this.getMinimumSize().getHeight() - fontRenderer.FONT_HEIGHT;
                break;
            case MIDDLE_RIGHT:
                x = this.getMinimumSize().getWidth() - this.getMinimumSize().getWidth();
                y = this.getMinimumSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
                break;
            case MIDDLE_CENTER:
                x = this.getMinimumSize().getWidth() / 2 - this.getMinimumSize().getWidth() / 2;
                y = this.getMinimumSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
                break;
            case MIDDLE_LEFT:
                x = 0;
                y = this.getMinimumSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
                break;
            case TOP_RIGHT:
                x = this.getMinimumSize().getWidth() - this.getMinimumSize().getWidth();
                y = 0;
                break;
            case TOP_CENTER:
                x = this.getMinimumSize().getWidth() / 2 - this.getMinimumSize().getWidth() / 2;
                y = 0;
                break;
            case TOP_LEFT:
            default:
                x = 0;
                y = 0;
                break;
            }
            Point labelPos = new Point(myGlobalPos.getX() + x, myGlobalPos.getY() + y);

            this.drawString(fontRenderer, this.text, labelPos.getX(), labelPos.getY(),
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
}
