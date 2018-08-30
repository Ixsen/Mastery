package masteryUI.elements.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.util.ReadableColor;

import masteryUI.elements.core.UIContainer;
import masteryUI.event.UIKeyEvent;
import masteryUI.functions.Typeable;

/**
 * @author Subaro
 */
public class UIKeyTest extends UILabel implements Typeable {

    private List<Consumer<UIKeyEvent>> onKeyTypedListener = new ArrayList<>();

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
        for (Consumer<UIKeyEvent> consumer : this.onKeyTypedListener) {
            consumer.accept(new UIKeyEvent(this, typedChar, keyCode));
        }
    }

}
