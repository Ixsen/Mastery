package masteryUI.functions;

import masteryUI.event.UIEventRunnable;

/**
 * @author Subaro
 */
public interface Clickable {

    void addClickListener(UIEventRunnable onCLick);

    void onClick();
}
