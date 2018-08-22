package mastery.ui.custom.functions;

/**
 * @author Subaro
 */
public interface Activatable {
    boolean isActive();

    void setActive(boolean activeState);

    void onActiveChanged(ActiveStateRunnable runnable);
}
