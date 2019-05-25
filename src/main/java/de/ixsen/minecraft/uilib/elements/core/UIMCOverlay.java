package de.ixsen.minecraft.uilib.elements.core;

import org.lwjgl.util.Point;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Base class with helper methods for easier creation of overlays
 */
public abstract class UIMCOverlay extends UIMCScreen {

    private long visibilityStart = 0;
    private long visibilityTimeFrame = 0;

    public UIMCOverlay(Point position) {
        super(position);

        this.initGui();
    }

    public UIMCOverlay() {
        this.initGui();
    }

    @SubscribeEvent
    public void drawOverlay(RenderGameOverlayEvent event) {
        if (this.shouldBeShown()) {
            this.drawScreen(0, 0, 1);
        }
    }

    private boolean shouldBeShown() {
        return (System.currentTimeMillis() - this.visibilityStart) < this.visibilityTimeFrame;
    }

    /**
     * Makes the overlay visible permanently
     */
    public void setVisible() {
        this.visibilityStart = Long.MAX_VALUE;
    }

    /**
     * Makes the overlay invisible permanently
     */
    public void setHidden() {
        this.visibilityStart = System.currentTimeMillis();
        this.visibilityTimeFrame = 0;
    }

    public void setVisibleTemporarily(long ms) {
        this.visibilityStart = System.currentTimeMillis();
        this.visibilityTimeFrame = ms;
    }
}
