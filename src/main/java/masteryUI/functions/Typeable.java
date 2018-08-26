package masteryUI.functions;

import masteryUI.event.UIEventRunnable;

/**
 * @author Subaro
 */
public interface Typeable {

    void addTypeListener(UIEventRunnable onType);

    void onType();

}
