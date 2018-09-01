package masteryUI.elements.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.ReadableColor;

import masteryUI.elements.core.UIContainer;
import masteryUI.event.UIFocusEvent;
import masteryUI.event.UIKeyEvent;
import masteryUI.functions.Focusable;
import masteryUI.functions.Typeable;

/**
 * UI element that displays the typed keys when it is focus.
 * 
 * @author Subaro
 */
public class UIKeyTest extends UILabel implements Typeable, Focusable {

    private boolean focus = false;
    private List<Consumer<UIKeyEvent>> onKeyTypedListener = new ArrayList<>();
    private List<Consumer<UIFocusEvent>> onFocusListener = new ArrayList<>();

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
            for (Consumer<UIKeyEvent> consumer : this.onKeyTypedListener) {
                consumer.accept(new UIKeyEvent(this, typedChar, keyCode));
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
}
