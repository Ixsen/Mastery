package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIEvent;

/**
 * @author Subaro
 */
public interface Updatable {

    void addUpdateListener(Consumer<UIEvent> onUpdate);

    void onUpdate();
}
