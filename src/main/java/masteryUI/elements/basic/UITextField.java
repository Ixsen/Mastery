package masteryUI.elements.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import masteryUI.colors.UIColors;
import masteryUI.elements.basic.UILabel.UIAlignment;
import masteryUI.elements.core.UIContainer;
import masteryUI.elements.core.UIScalableElement;
import masteryUI.event.UIFocusEvent;
import masteryUI.event.UIKeyEvent;
import masteryUI.event.UIValueChangeEvent;
import masteryUI.functions.Changable;
import masteryUI.functions.Focusable;
import masteryUI.functions.Typeable;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatAllowedCharacters;

/**
 * @author Subaro
 */
public class UITextField extends UIScalableElement implements Typeable, Focusable, Changable<String> {

    /** Text to show */
    private String text;
    /** Text to show when the element is not focused and the current text is empty */
    private String placeholderText;
    /** Alignment for this lable */
    private UIAlignment alignment;
    /** Determines whether this element is currently focused. */
    private boolean focus = false;
    /** Color for the text element */
    private ReadableColor textColor;
    /** Color for the caret element */
    private ReadableColor caretColor = ReadableColor.LTGREY;
    /** Color for the placeholder element */
    private ReadableColor placeHolderColor = ReadableColor.LTGREY;
    /** List of consumer that are executed when a key event is fired. */
    private List<Consumer<UIKeyEvent>> onKeyTypedListener = new ArrayList<>();
    /** List of consumer that are executed when the focus of this element changes. */
    private List<Consumer<UIFocusEvent>> onFocusListener = new ArrayList<>();
    /** List of consumer that are executed when the value of this element changes. */
    private List<Consumer<UIValueChangeEvent<String>>> onChangeListener = new ArrayList<>();
    /** Determines how fast the caret should blink. */
    int maxTicks = 50;
    /** Used for the blinking of the caret. */
    int currentTicks = 0;

    public UITextField(String text, String placeholderText, ReadableColor textColor, float scale,
            UIAlignment alignment) {
        super(scale);
        this.placeholderText = placeholderText;
        this.text = text;
        this.textColor = textColor;
        this.alignment = alignment;
    }

    public UITextField(UIContainer parentContainer, String placeholderText, String text, ReadableColor textColor,
            float scale,
            UIAlignment alignment) {
        super(parentContainer, scale);
        this.placeholderText = placeholderText;
        this.text = text;
        this.textColor = textColor;
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
                    this.mc.fontRenderer.getStringWidth(this.getText() + typedChar + "I") + 6,
                    this.mc.fontRenderer.FONT_HEIGHT);
            // Only add text if in bounds
            if (calculatedSize.getWidth() < this.getMinimumSize().getWidth()) {
                this.setText(this.getText() + typedChar);
            }
        } else if (keyCode == 14) {
            // Backspace remove one text element
            if (this.text.length() > 0) {
                this.setText(this.text.substring(0, this.text.length() - 1));
            }
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
        }
    }

    @Override
    public boolean isFocused() {
        return this.focus;
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        {
            Point myGlobalPos = this.getGlobalPosition(parentX, parentY);
            // Draw Background
            this.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);

            // Increase Ticks
            this.currentTicks += 1;

            // Draw 'label'
            String drawString = this.getText();
            if (this.text.isEmpty() && !this.isFocused()) {
                drawString = this.placeholderText;
            }

            FontRenderer fontRenderer = this.mc.fontRenderer;
            ReadableDimension calculatedSize = new Dimension(fontRenderer.getStringWidth(drawString),
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

            if (this.text.isEmpty() && !this.isFocused()) {
                Point labelPos = new Point(myGlobalPos.getX() + x, myGlobalPos.getY() + y);
                this.drawString(this.mc.fontRenderer, drawString, labelPos.getX(), labelPos.getY(),
                        UIColors.toInt(this.getPlaceHolderColor()));
            } else {
                Point labelPos = new Point(myGlobalPos.getX() + x, myGlobalPos.getY() + y);
                this.drawString(this.mc.fontRenderer, drawString, labelPos.getX(), labelPos.getY(),
                        UIColors.toInt(this.getTextColor()));
                if (this.isFocused()) {
                    if (this.currentTicks <= this.maxTicks) {
                        Point caretPos = new Point(labelPos.getX() + calculatedSize.getWidth(), labelPos.getY());
                        this.drawString(this.mc.fontRenderer, "I", caretPos.getX(), caretPos.getY(),
                                UIColors.toInt(this.caretColor));
                    } else if (this.currentTicks > 2 * this.maxTicks) {
                        this.currentTicks = 0;
                    }
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        if (!this.getText().equals(text)) {
            this.text = text;
            // Text changed
            this.onValueChanged();
        }
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

    public ReadableColor getCaretColor() {
        return this.caretColor;
    }

    public void setCaretColor(ReadableColor caretColor) {
        this.caretColor = caretColor;
    }

    public ReadableColor getPlaceHolderColor() {
        return this.placeHolderColor;
    }

    public void setPlaceHolderColor(ReadableColor placeHolderColor) {
        this.placeHolderColor = placeHolderColor;
    }
}