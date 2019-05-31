package de.ixsen.minecraft.uilib.elements.core;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import org.lwjgl.util.Point;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Base class with helper methods for easier creation of overlays
 */
public abstract class MasteryGuiOverlay<CONTAINER_TYPE extends GuiContainer> extends MasteryGuiScreen<CONTAINER_TYPE> {

    private long visibilityStart = 0;
    private long visibilityTimeFrame = 0;

    public MasteryGuiOverlay(Point position) {
        super(position);

        this.initGui();
    }

    public MasteryGuiOverlay() {
        this.initGui();
    }

    @SubscribeEvent
    public void drawOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && this.shouldBeShown()) {
            this.drawScreen(0, 0, event.getPartialTicks());
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
