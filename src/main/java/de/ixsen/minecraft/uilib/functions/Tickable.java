package de.ixsen.minecraft.uilib.functions;

import java.util.function.Consumer;

import de.ixsen.minecraft.uilib.event.UIEvent;

/**
 * @author Subaro
 */
public interface Tickable {

    void addTickListener(Consumer<UIEvent> onTick);

    void onTick();
}
