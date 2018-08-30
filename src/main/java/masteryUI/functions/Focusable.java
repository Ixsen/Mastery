package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIEvent;

/**
 * @author Subaro
 */
public interface Focusable {

    void addFocusChangeListener(Consumer<UIEvent> onFocusChanged);

    void onFocusChanged();
}
