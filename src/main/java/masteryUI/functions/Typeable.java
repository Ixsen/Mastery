package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIKeyEvent;

/**
 * @author Subaro
 */
public interface Typeable {

    /**
     * Adds a new consumer to the element.
     */
    void addTypeListener(Consumer<UIKeyEvent> onType);

    /**
     * Called when a key event is fired.
     */
    void onKeyTyped(char typedChar, int keyCode);
}
