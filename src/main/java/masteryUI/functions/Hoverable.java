package masteryUI.functions;

import masteryUI.event.UIEventRunnable;

/**
 * @author Subaro
 */
public interface Hoverable {

    void addHoverListener(UIEventRunnable onHover);

    void onHover();
}
