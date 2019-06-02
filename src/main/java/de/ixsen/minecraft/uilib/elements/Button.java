package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.mastery.ui.resources.UIResourceLocationManager;
import de.ixsen.minecraft.uilib.common.Alignment;
import de.ixsen.minecraft.uilib.common.ColorUtils;
import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.ClickableUiElement;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

/**
 * Represents a scalable button.
 *
 * @author Subaro
 */
public class Button extends ClickableUiElement {

    // TOP
    private static final Point TOP_LEFT_POS = new Point(0, 0);
    private static final Point TOP_LEFT_SIZE = new Point(2, 2);
    private static final Point TOP_CENTER_POS = new Point(2, 0);
    private static final Point TOP_CENTER_SIZE = new Point(196, 2);
    private static final Point TOP_RIGHT_POS = new Point(198, 0);
    private static final Point TOP_RIGHT_SIZE = new Point(2, 2);

    // MIDDLE
    private static final Point MIDDLE_LEFT_POS = new Point(0, 2);
    private static final Point MIDDLE_LEFT_SIZE = new Point(2, 15);
    private static final Point MIDDLE_CENTER_POS = new Point(2, 2);
    private static final Point MIDDLE_CENTER_SIZE = new Point(196, 15);
    private static final Point MIDDLE_RIGHT_POS = new Point(198, 2);
    private static final Point MIDDLE_RIGHT_SIZE = new Point(2, 15);

    // BOT
    private static final Point BOT_LEFT_POS = new Point(0, 17);
    private static final Point BOT_LEFT_SIZE = new Point(3, 3);
    private static final Point BOT_CENTER_POS = new Point(2, 17);
    private static final Point BOT_CENTER_SIZE = new Point(196, 3);
    private static final Point BOT_RIGHT_POS = new Point(198, 17);
    private static final Point BOT_RIGHT_SIZE = new Point(2, 3);

    /** used to indicate that the element was just clicked. */
    private boolean justClicked = false;
    /** Text to show */
    private String text;
    /** Color for the text element */
    private ReadableColor textColor;
    /** Alignment for this lable */
    private Alignment alignment;

    public Button(String text) {
        super(1);
        this.text = text;
        this.textColor = ReadableColor.WHITE;
        this.alignment = Alignment.MIDDLE_CENTER;
        this.setSize(150, 20);
    }

    public Button(UiContainer parentContainer, String text) {
        super(parentContainer, 1);
        this.text = text;
        this.textColor = ReadableColor.WHITE;
        this.setSize(150, 20);
    }

    @Override
    public void drawBackground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        int offsetY = 0;
        if (!this.isEnabled()) {
            // Draw disabled background
            offsetY = 0;
            this.textColor = ReadableColor.DKGREY;
        } else if (this.justClicked) {
            // Draw clicked background
            this.justClicked = false;
            offsetY = 60;
            this.textColor = ReadableColor.YELLOW;
        } else if (this.isMouseHovering(mouseX, mouseY)) {
            // Draw hover background
            offsetY = 40;
            this.textColor = ReadableColor.YELLOW;
        } else {
            // Draw default background
            offsetY = 20;
            this.textColor = ReadableColor.WHITE;
        }

        // Prepare information before drawing the background
        ReadableDimension size = this.getMinimumSize();
        this.mc.renderEngine.bindTexture(UIResourceLocationManager.WIDGETS_ATLAS);
        int innerWidth = size.getWidth() - Button.TOP_LEFT_SIZE.getX() - Button.TOP_RIGHT_SIZE.getX();
        int innerHeight = size.getHeight() - Button.TOP_LEFT_SIZE.getY() - Button.BOT_LEFT_SIZE.getY();

        // Draw Top Left
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX(), this.getGlobalPosition().getY(),
                Button.TOP_LEFT_POS.getX(), Button.TOP_LEFT_POS.getY() + offsetY, Button.TOP_LEFT_SIZE.getX(),
                Button.TOP_LEFT_SIZE.getY(), Button.TOP_LEFT_SIZE.getX(), Button.TOP_LEFT_SIZE.getY(), 512, 512);

        // Draw Top Center
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX() + Button.TOP_LEFT_SIZE.getX(),
                this.getGlobalPosition().getY(), Button.TOP_CENTER_POS.getX(), Button.TOP_CENTER_POS.getY() + offsetY,
                Button.TOP_CENTER_SIZE.getX(), Button.TOP_CENTER_SIZE.getY(), innerWidth, Button.TOP_CENTER_SIZE.getY(),
                512, 512);

        // Draw Top Right
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX() + Button.TOP_LEFT_SIZE.getX() + innerWidth,
                this.getGlobalPosition().getY(), Button.TOP_RIGHT_POS.getX(), Button.TOP_RIGHT_POS.getY() + offsetY,
                Button.TOP_RIGHT_SIZE.getX(), Button.TOP_RIGHT_SIZE.getY(), Button.TOP_RIGHT_SIZE.getX(),
                Button.TOP_RIGHT_SIZE.getY(), 512, 512);

        // Draw Middle Left
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX(),
                this.getGlobalPosition().getY() + Button.TOP_LEFT_SIZE.getY(), Button.MIDDLE_LEFT_POS.getX(),
                Button.MIDDLE_LEFT_POS.getY() + offsetY, Button.MIDDLE_LEFT_SIZE.getX(), Button.MIDDLE_LEFT_SIZE.getY(),
                Button.MIDDLE_LEFT_SIZE.getX(), innerHeight, 512, 512);

        // Draw Middle Center
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX() + Button.TOP_LEFT_SIZE.getX(),
                this.getGlobalPosition().getY() + Button.TOP_LEFT_SIZE.getY(), Button.MIDDLE_CENTER_POS.getX(),
                Button.MIDDLE_CENTER_POS.getY() + offsetY, Button.MIDDLE_CENTER_SIZE.getX(),
                Button.MIDDLE_CENTER_SIZE.getY(), innerWidth, innerHeight, 512, 512);

        // Draw Middle Right
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX() + Button.TOP_LEFT_SIZE.getX() + innerWidth,
                this.getGlobalPosition().getY() + Button.TOP_RIGHT_SIZE.getY(), Button.MIDDLE_RIGHT_POS.getX(),
                Button.MIDDLE_RIGHT_POS.getY() + offsetY, Button.MIDDLE_RIGHT_SIZE.getX(),
                Button.MIDDLE_RIGHT_SIZE.getY(), Button.MIDDLE_RIGHT_SIZE.getX(), innerHeight, 512, 512);

        // Draw Bot Left
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX(),
                this.getGlobalPosition().getY() + Button.TOP_LEFT_SIZE.getY() + innerHeight, Button.BOT_LEFT_POS.getX(),
                Button.BOT_LEFT_POS.getY() + offsetY, Button.BOT_LEFT_SIZE.getX(), Button.BOT_LEFT_SIZE.getY(),
                Button.BOT_LEFT_SIZE.getX(), Button.BOT_LEFT_SIZE.getY(), 512, 512);

        // Draw Bot Center
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX() + Button.TOP_LEFT_SIZE.getX(),
                this.getGlobalPosition().getY() + Button.TOP_LEFT_SIZE.getY() + innerHeight,
                Button.BOT_CENTER_POS.getX(), Button.BOT_CENTER_POS.getY() + offsetY, Button.BOT_CENTER_SIZE.getX(),
                Button.BOT_CENTER_SIZE.getY(), innerWidth, Button.BOT_CENTER_SIZE.getY(), 512, 512);

        // Draw Bot Right
        Gui.drawScaledCustomSizeModalRect(this.getGlobalPosition().getX() + Button.TOP_LEFT_SIZE.getX() + innerWidth,
                this.getGlobalPosition().getY() + Button.TOP_RIGHT_SIZE.getY() + innerHeight,
                Button.BOT_RIGHT_POS.getX(), Button.BOT_RIGHT_POS.getY() + offsetY, Button.BOT_RIGHT_SIZE.getX(),
                Button.BOT_RIGHT_SIZE.getY(), Button.BOT_RIGHT_SIZE.getX(), Button.BOT_RIGHT_SIZE.getY(), 512, 512);
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (!this.text.equals("")) {
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

            Point labelPos = new Point(this.getGlobalPosition().getX() + x, this.getGlobalPosition().getY() + y);
            this.drawString(this.mc.fontRenderer, this.text, labelPos.getX(), labelPos.getY(),
                    ColorUtils.toInt(this.textColor));
        }
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

    public Alignment getAlignment() {
        return this.alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    @Override
    public boolean onClick(int mouseX, int mouseY, int mouseButton) {
        this.justClicked = true;
        return super.onClick(mouseX, mouseY, mouseButton);
    }
}
