package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIEvent;

/**
 * @author Subaro
 */
public interface Changable {

    void addChangeListener(Consumer<UIEvent> onValueChange);

    void onValueChanged();

}
