package masteryUI.functions;

import masteryUI.event.UIEventRunnable;

/**
 * @author Subaro
 */
public interface Focusable {

    void addFocusChangeListener(UIEventRunnable onFocusChanged);

    void onFocusChanged();
}
