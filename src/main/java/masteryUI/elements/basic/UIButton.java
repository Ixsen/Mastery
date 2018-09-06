package masteryUI.elements.basic;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import mastery.MasteryMod;
import masteryUI.colors.UIColors;
import masteryUI.elements.basic.UILabel.UIAlignment;
import masteryUI.elements.core.UIClickableElement;
import masteryUI.elements.core.UIContainer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Represents a scalable button.
 *
 * @author Subaro
 */
public class UIButton extends UIClickableElement {
    /** Resource containing the graphical elements used to draw the button. */
    protected static final ResourceLocation WIDGETS_ATLAS = new ResourceLocation(MasteryMod.modid,
            "textures/gui/widgets.png");

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
    /** Text to show */
    private String text;
    /** Color for the text element */
    private ReadableColor textColor;
    /** Alignment for this lable */
    private UIAlignment alignment;

    public UIButton(String text, ReadableColor textColor, float scale, UIAlignment alignment) {
        super(scale);
        this.text = text;
        this.textColor = textColor;
        this.alignment = alignment;
        this.setSize(150, 20);
    }

    public UIButton(UIContainer parentContainer, String text, ReadableColor textColor, float scale,
            UIAlignment alignment) {
        super(parentContainer, scale);
        this.text = text;
        this.textColor = textColor;
        this.setSize(150, 20);
    }

    @Override
    public void drawBackground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        int offsetY = 0;
        if (!this.isEnabled()) {
            // Draw disabled background
            offsetY = 0;
        } else if (this.isClicked()) {
            // Draw clicked background
            offsetY = 60;
        } else if (this.isMouseHovering(mouseX, mouseY)) {
            // Draw hover background
            offsetY = 40;
        } else {
            // Draw default background
            offsetY = 20;
        }

        // Prepare information before drawing the background
        ReadableDimension size = this.getMinimumSize();
        Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
        this.mc.renderEngine.bindTexture(WIDGETS_ATLAS);
        int innerWidth = size.getWidth() - TOP_LEFT_SIZE.getX() - TOP_RIGHT_SIZE.getX();
        int innerHeight = size.getHeight() - TOP_LEFT_SIZE.getY() - BOT_LEFT_SIZE.getY();

        // Draw Top Left
        drawScaledCustomSizeModalRect(myGlobalPos.getX(), myGlobalPos.getY(), TOP_LEFT_POS.getX(),
                TOP_LEFT_POS.getY() + offsetY, TOP_LEFT_SIZE.getX(), TOP_LEFT_SIZE.getY(), TOP_LEFT_SIZE.getX(),
                TOP_LEFT_SIZE.getY(), 512, 512);

        // Draw Top Center
        drawScaledCustomSizeModalRect(myGlobalPos.getX() + TOP_LEFT_SIZE.getX(), myGlobalPos.getY(),
                TOP_CENTER_POS.getX(),
                TOP_CENTER_POS.getY() + offsetY, TOP_CENTER_SIZE.getX(), TOP_CENTER_SIZE.getY(), innerWidth,
                TOP_CENTER_SIZE.getY(), 512, 512);

        // Draw Top Right
        drawScaledCustomSizeModalRect(myGlobalPos.getX() + TOP_LEFT_SIZE.getX() + innerWidth, myGlobalPos.getY(),
                TOP_RIGHT_POS.getX(),
                TOP_RIGHT_POS.getY() + offsetY, TOP_RIGHT_SIZE.getX(), TOP_RIGHT_SIZE.getY(), TOP_RIGHT_SIZE.getX(),
                TOP_RIGHT_SIZE.getY(), 512, 512);

        // Draw Middle Left
        drawScaledCustomSizeModalRect(myGlobalPos.getX(), myGlobalPos.getY() + TOP_LEFT_SIZE.getY(),
                MIDDLE_LEFT_POS.getX(),
                MIDDLE_LEFT_POS.getY() + offsetY, MIDDLE_LEFT_SIZE.getX(), MIDDLE_LEFT_SIZE.getY(),
                MIDDLE_LEFT_SIZE.getX(),
                innerHeight, 512, 512);

        // Draw Middle Center
        drawScaledCustomSizeModalRect(myGlobalPos.getX() + TOP_LEFT_SIZE.getX(),
                myGlobalPos.getY() + TOP_LEFT_SIZE.getY(),
                MIDDLE_CENTER_POS.getX(),
                MIDDLE_CENTER_POS.getY() + offsetY, MIDDLE_CENTER_SIZE.getX(), MIDDLE_CENTER_SIZE.getY(), innerWidth,
                innerHeight, 512, 512);

        // Draw Middle Right
        drawScaledCustomSizeModalRect(myGlobalPos.getX() + TOP_LEFT_SIZE.getX() + innerWidth,
                myGlobalPos.getY() + TOP_RIGHT_SIZE.getY(),
                MIDDLE_RIGHT_POS.getX(),
                MIDDLE_RIGHT_POS.getY() + offsetY, MIDDLE_RIGHT_SIZE.getX(), MIDDLE_RIGHT_SIZE.getY(),
                MIDDLE_RIGHT_SIZE.getX(),
                innerHeight, 512, 512);

        // Draw Bot Left
        drawScaledCustomSizeModalRect(myGlobalPos.getX(), myGlobalPos.getY() + TOP_LEFT_SIZE.getY() + innerHeight,
                BOT_LEFT_POS.getX(),
                BOT_LEFT_POS.getY() + offsetY, BOT_LEFT_SIZE.getX(), BOT_LEFT_SIZE.getY(),
                BOT_LEFT_SIZE.getX(),
                BOT_LEFT_SIZE.getY(), 512, 512);

        // Draw Bot Center
        drawScaledCustomSizeModalRect(myGlobalPos.getX() + TOP_LEFT_SIZE.getX(),
                myGlobalPos.getY() + TOP_LEFT_SIZE.getY() + innerHeight,
                BOT_CENTER_POS.getX(),
                BOT_CENTER_POS.getY() + offsetY, BOT_CENTER_SIZE.getX(), BOT_CENTER_SIZE.getY(), innerWidth,
                BOT_CENTER_SIZE.getY(), 512, 512);

        // Draw Bot Right
        drawScaledCustomSizeModalRect(myGlobalPos.getX() + TOP_LEFT_SIZE.getX() + innerWidth,
                myGlobalPos.getY() + TOP_RIGHT_SIZE.getY() + innerHeight,
                BOT_RIGHT_POS.getX(),
                BOT_RIGHT_POS.getY() + offsetY, BOT_RIGHT_SIZE.getX(), BOT_RIGHT_SIZE.getY(),
                BOT_RIGHT_SIZE.getX(),
                BOT_RIGHT_SIZE.getY(), 512, 512);
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        {
            Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
            // Draw background
            this.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);

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
