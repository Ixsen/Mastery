package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIEvent;

/**
 * @author Subaro
 */
public interface Tickable {

    void addTickListener(Consumer<UIEvent> onTick);

    void onTick();
}
