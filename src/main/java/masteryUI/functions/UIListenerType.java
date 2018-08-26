package masteryUI.functions;

/**
 * @author Subaro
 */
public enum UIListenerType {

    // Visibility Type (all elements)
    ON_VISIBLE_CHANGED,

    // Enable Type (all elements)
    ON_ENABLE_CHANGED,

    // Mouse Types
    ON_MOUSE_HOVER, ON_MOUSE_CLICK, ON_MOUSE_RELEASE, ON_MOUSE_DRAG,

    // Key Types
    ON_KEY_DOWN, ON_KEY_UP,

    // Focus Types
    ON_FOCUS, ON_UNFOCUS,

    // Tick Type
    ON_TICK,

    // Changeable Types
    ON_VALUE_CHANGED,

    // Update Type
    ON_UPDATE
}
