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
import masteryUI.event.UIFocusEvent;
import masteryUI.event.UIKeyEvent;
import masteryUI.event.UIValueChangeEvent;
import masteryUI.functions.Changable;
import masteryUI.functions.Focusable;
import masteryUI.functions.Typeable;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatAllowedCharacters;

/**
 * UI element that displays the typed keys when it is focus.
 *
 * @author Subaro
 */
public class UIKeyTest extends UILabel implements Typeable, Focusable, Changable<String> {

    /** Determines whether this element is currently focused. */
    private boolean focus = false;
    /** List of consumer that are executed when a key event is fired. */
    private List<Consumer<UIKeyEvent>> onKeyTypedListener = new ArrayList<>();
    /** List of consumer that are executed when the focus of this element changes. */
    private List<Consumer<UIFocusEvent>> onFocusListener = new ArrayList<>();
    /** List of consumer that are executed when the value of this element changes. */
    private List<Consumer<UIValueChangeEvent<String>>> onChangeListener = new ArrayList<>();

    public UIKeyTest(String text, ReadableColor textColor, float scale, UILabelAlignment alignment) {
        super(text, textColor, scale, alignment);
    }

    public UIKeyTest(UIContainer parentContainer, String text, ReadableColor textColor, float scale,
            UILabelAlignment alignment) {
        super(parentContainer, text, textColor, scale, alignment);
    }

    @Override
    public void addTypeListener(Consumer<UIKeyEvent> onType) {
        this.onKeyTypedListener.add(onType);
    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {
        if (this.isFocused()) {
            this.handleKey(typedChar, keyCode);
            for (Consumer<UIKeyEvent> consumer : this.onKeyTypedListener) {
                consumer.accept(new UIKeyEvent(this, typedChar, keyCode));
            }
        }
    }

    /**
     * Processes the key input for the text field
     */
    private void handleKey(char typedChar, int keyCode) {
        if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
            ReadableDimension calculatedSize = new Dimension(
                    this.mc.fontRenderer.getStringWidth(this.getText() + typedChar + "|"),
                    this.mc.fontRenderer.FONT_HEIGHT);
            // Only add text if in bounds
            if (calculatedSize.getWidth() < this.getMinimumSize().getWidth()) {
                this.setText(this.getText() + typedChar);
            }
        }
    }

    @Override
    public void setText(String text) {
        if (!this.getText().equals(text)) {
            super.setText(text);
            // Text changed
            this.onValueChanged();
        }
    }

    @Override
    public void addFocusChangeListener(Consumer<UIFocusEvent> onFocusChanged) {
        this.onFocusListener.add(onFocusChanged);
    }

    @Override
    public void setFocused(boolean focus) {
        // Focus changed, fire event
        if (this.focus != focus) {
            this.focus = focus;
            for (Consumer<UIFocusEvent> consumer : this.onFocusListener) {
                consumer.accept(new UIFocusEvent(this, !this.focus, this.focus));
            }
            if (this.isFocused()) {
                this.mc.player.sendChatMessage("Object is focused");
            } else {
                this.mc.player.sendChatMessage("Object is not longer focused");
            }
        }
    }

    @Override
    public boolean isFocused() {
        return this.focus;
    }

    int maxTicks = 50;
    int currentTicks = 0;

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        {
            Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
            // Draw background
            this.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);

            this.currentTicks += 1;

            // Draw 'label'
            FontRenderer fontRenderer = this.mc.fontRenderer;
            String showedText = this.getText();
            ReadableDimension calculatedSize = new Dimension(fontRenderer.getStringWidth(showedText),
                    fontRenderer.FONT_HEIGHT);
            int y, x;

            switch (this.getAlignment()) {
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
            this.drawString(this.mc.fontRenderer, showedText, labelPos.getX(), labelPos.getY(),
                    UIColors.toInt(this.getTextColor()));
            if (this.isFocused()) {
                if (this.currentTicks <= this.maxTicks) {
                    Point caretPos = new Point(labelPos.getX() + calculatedSize.getWidth(), labelPos.getY());
                    this.drawString(this.mc.fontRenderer, "|", caretPos.getX(), caretPos.getY(),
                            UIColors.toInt(this.getTextColor()));
                } else if (this.currentTicks > 2 * this.maxTicks) {
                    this.currentTicks = 0;
                }
            }
        }
        this.endScaling();
    }

    @Override
    public void addChangeListener(Consumer<UIValueChangeEvent<String>> onValueChange) {
        this.onChangeListener.add(onValueChange);
    }

    @Override
    public void onValueChanged() {
        for (Consumer<UIValueChangeEvent<String>> consumer : this.onChangeListener) {
            consumer.accept(new UIValueChangeEvent<String>(this, this.getText()));
        }
    }
}
