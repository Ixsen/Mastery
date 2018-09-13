package mastery.ui.resources;

import mastery.MasteryMod;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UIResourceLocationManager {
    // Widgets
    public static final ResourceLocation WIDGETS_ATLAS = new ResourceLocation(MasteryMod.modid,
            "textures/gui/widgets.png");

    // Fluids
    // FLUID - EXP
    public static ResourceLocation FLUID_EXP_STILL = new ResourceLocation(MasteryMod.modid,
            "textures/blocks/xp_juice_still.png");
    public static ResourceLocation FLUID_EXP_FLOWING = new ResourceLocation(MasteryMod.modid,
            "textures/blocks/xp_juice_flowing.png");
    // FLUID - WATER
    public static ResourceLocation FLUID_WATER_STILL = new ResourceLocation("textures/blocks/water_flow.png");
    public static ResourceLocation FLUID_WATER_FLOWING = new ResourceLocation("textures/blocks/water_still.png");
    // FLUID - LAVA
    public static ResourceLocation FLUID_LAVA_STILL = new ResourceLocation("textures/blocks/lava_still.png");
    public static ResourceLocation FLUID_LAVA_FLOWING = new ResourceLocation("textures/blocks/lava_flow.png");

    // Tiled Backgrounds
    // Oak - Wood
    public static final ResourceLocation IMAGE_TILED_OAK = new ResourceLocation(MasteryMod.modid,
            "textures/gui/tiled/oak_planks.png");

    // Tiled Animated Backgrounds
    // Magma
    public static final ResourceLocation ANIMATED_IMAGE_TILED_MAGMA = new ResourceLocation(MasteryMod.modid,
            "textures/gui/tiled/magma.png");
    // Prismarine
    public static final ResourceLocation ANIMATED_IMAGE_TILED_PRISMARINE = new ResourceLocation(MasteryMod.modid,
            "textures/gui/tiled/prismarine.png");
    // Sea lantern
    public static final ResourceLocation ANIMATED_IMAGE_TILED_SEA_LANTERN = new ResourceLocation(MasteryMod.modid,
            "textures/gui/tiled/sea_lantern.png");

}
