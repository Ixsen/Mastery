package masteryUI.elements.core;

import java.util.HashMap;

import masteryUI.event.UIMouseEvent;
import masteryUI.layout.LayoutData;

/**
 * @author Subaro
 */
public abstract class UIContainer extends UIElement {

    /** List containg all the ui elemnts that are assigned to this container. */
    private final HashMap<UIElement, LayoutData> children;

    public UIContainer() {
        super();
        this.children = new HashMap<>();
    }

    public UIContainer(UIContainer parentContainer) {
        super(parentContainer);
        this.children = new HashMap<>();
        this.addVisibilityChangeListener(this::test);
    }

    /**
     * override this method to add elements to this container
     */
    abstract void onInit();

    private void test(UIMouseEvent event) {

    }
}