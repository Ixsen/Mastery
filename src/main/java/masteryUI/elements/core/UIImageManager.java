package masteryUI.elements.core;

import mastery.MasteryMod;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UIImageManager {

    /** Resource containing the graphical elements used to draw the buttons and other widgets. */
    public static final ResourceLocation WIDGETS_ATLAS = new ResourceLocation(MasteryMod.modid,
            "textures/gui/widgets.png");

    // UI Fluid Bar Gauge
    public static UIImageData UI_FLUID_BAR_CONTAINER = new UIImageData(WIDGETS_ATLAS, 200, 0, 18, 49, 512, 512);
}
