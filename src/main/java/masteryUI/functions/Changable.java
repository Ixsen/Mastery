package masteryUI.functions;

import masteryUI.event.UIEventRunnable;

/**
 * @author Subaro
 */
public interface Changable {

    void addChangeListener(UIEventRunnable onValueChange);

    void onValueChanged();

}
