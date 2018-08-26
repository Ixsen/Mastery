package masteryUI.functions;

import masteryUI.event.UIEventRunnable;

/**
 * @author Subaro
 */
public interface Updatable {

    void addUpdateListener(UIEventRunnable onUpdate);

    void onUpdate();
}
