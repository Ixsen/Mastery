package masteryUI.functions;

import masteryUI.event.UIEventRunnable;

/**
 * @author Subaro
 */
public interface Tickable {

    void addTickListener(UIEventRunnable onTick);

    void onTick();
}
