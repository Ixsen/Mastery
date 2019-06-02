package de.ixsen.minecraft.uilib.functions;

import java.util.function.Consumer;

import de.ixsen.minecraft.uilib.event.UIEvent;

/**
 * @author Subaro
 */
public interface Updatable {

    void addUpdateListener(Consumer<UIEvent> onUpdate);

    void onUpdate();
}
