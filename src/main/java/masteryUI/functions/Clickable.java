package masteryUI.functions;

import java.util.function.Consumer;

import masteryUI.event.UIMouseEvent;

/**
 * @author Subaro
 */
public interface Clickable {

    void addClickListener(Consumer<UIMouseEvent> onClick);

    /**
     * Is called when the element clicked.
     * 
     * @return true, if the element should be omitted.
     */
    boolean onClick(int mouseX, int mouseY, int mouseButton);
}
