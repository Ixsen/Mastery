package masteryUI.elements.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import masteryUI.colors.UIColors;
import masteryUI.elements.core.UIContainer;
import masteryUI.elements.core.UIScalableElement;
import masteryUI.event.UIMouseEvent;
import masteryUI.functions.Draggable;
import net.minecraft.client.gui.FontRenderer;

/**
 * @author Subaro
 */
public class UILabel extends UIScalableElement implements Draggable {

    /**
     * Determines the alignment of the ui label.
     */
    public enum UILabelAlignment {
    TOP_LEFT, TOP_CENTER, TOP_RIGHT, MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT, BOT_LEFT, BOT_CENTER, BOT_RIGHT
    }

    /** Indicates that a label was clicked. Is used to determine if a drag event is associated to the UILabel */
    private boolean isClicked;
    /** Text to show */
    private String text;
    /** Color for the text element */
    private ReadableColor textColor;
    /** List containing the consumers for the on click event */
    private List<Consumer<UIMouseEvent>> onClickListener = new ArrayList<>();
    /** List containing the consumers for the on drag event */
    private List<Consumer<UIMouseEvent>> onDragListener = new ArrayList<>();
    /** List containing the consumers for the on release event */
    private List<Consumer<UIMouseEvent>> onReleaseListener = new ArrayList<>();
    /** Alignment for this lable */
    private UILabelAlignment alignment;

    public UILabel(String text, ReadableColor textColor, float scale, UILabelAlignment alignment) {
        super(scale);
        this.text = text;
        this.textColor = textColor;
        this.alignment = alignment;
    }

    public UILabel(UIContainer parentContainer, String text, ReadableColor textColor, float scale,
            UILabelAlignment alignment) {
        super(parentContainer, scale);
        this.text = text;
        this.textColor = textColor;
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        {
            int border = 0;
            Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
            this.startScissors(this.screen, myGlobalPos.getX() + border, myGlobalPos.getY() + border,
                    this.getMinimumSize().getWidth() - 2 * border, this.getMinimumSize().getHeight() - 2 * border);
            {
                // Draw background
                super.draw(parentX, parentY, mouseX, mouseY, partialTicks);

                // Draw 'label'
                FontRenderer fontRenderer = this.mc.fontRenderer;
                List<String> lines = fontRenderer.listFormattedStringToWidth(this.getText(),
                        this.getMinimumSize().getWidth());
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

                for (String line : lines) {
                    Point labelPos = new Point(myGlobalPos.getX() + x, myGlobalPos.getY() + y);
                    this.drawString(this.mc.fontRenderer, line, labelPos.getX(), labelPos.getY(),
                            UIColors.toInt(this.textColor));
                    y += fontRenderer.FONT_HEIGHT;
                }
            }
            this.endScissors();
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

    @Override
    public void addClickListener(Consumer<UIMouseEvent> onClick) {
        this.onClickListener.add(onClick);
    }

    @Override
    public boolean onClick(int mouseX, int mouseY, int mouseButton) {
        this.isClicked = true;
        for (Consumer<UIMouseEvent> consumer : this.onClickListener) {
            consumer.accept(new UIMouseEvent(this, new Point(mouseX, mouseY), mouseButton));
        }
        return true;
    }

    @Override
    public void addDragListener(Consumer<UIMouseEvent> onDrag) {
        this.onDragListener.add(onDrag);
    }

    @Override
    public boolean onDrag(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        for (Consumer<UIMouseEvent> consumer : this.onDragListener) {
            consumer.accept(new UIMouseEvent(this, new Point(mouseX, mouseY), clickedMouseButton, timeSinceLastClick));
        }
        return true;
    }

    @Override
    public void addReleaseListener(Consumer<UIMouseEvent> onRelease) {
        this.onReleaseListener.add(onRelease);
    }

    @Override
    public boolean onRelease(int mouseX, int mouseY, int state) {
        this.isClicked = false;
        for (Consumer<UIMouseEvent> consumer : this.onReleaseListener) {
            consumer.accept(new UIMouseEvent(this, new Point(mouseX, mouseY), 0, state));
        }
        return true;
    }

    @Override
    public boolean isClicked() {
        return this.isClicked;
    }
}
