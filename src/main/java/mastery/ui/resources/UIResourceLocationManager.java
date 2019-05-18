package mastery.ui.resources;

import mastery.MasteryMod;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public interface UIResourceLocationManager {
    // Widgets
    ResourceLocation WIDGETS_ATLAS = new ResourceLocation(MasteryMod.MOD_ID, "textures/gui/widgets.png");

    // Fluids
    // FLUID - EXP
    ResourceLocation FLUID_EXP_STILL = new ResourceLocation(MasteryMod.MOD_ID, "textures/blocks/xp_juice_still.png");
    ResourceLocation FLUID_EXP_FLOWING = new ResourceLocation(MasteryMod.MOD_ID,
            "textures/blocks/xp_juice_flowing.png");
    // FLUID - WATER
    ResourceLocation FLUID_WATER_STILL = new ResourceLocation("textures/blocks/water_flow.png");
    ResourceLocation FLUID_WATER_FLOWING = new ResourceLocation("textures/blocks/water_still.png");
    // FLUID - LAVA
    ResourceLocation FLUID_LAVA_STILL = new ResourceLocation("textures/blocks/lava_still.png");
    ResourceLocation FLUID_LAVA_FLOWING = new ResourceLocation("textures/blocks/lava_flow.png");

    // Tiled Backgrounds
    // Oak - Wood
    ResourceLocation IMAGE_TILED_OAK = new ResourceLocation(MasteryMod.MOD_ID, "textures/gui/tiled/oak_planks.png");

    // Tiled Animated Backgrounds
    // Magma
    ResourceLocation ANIMATED_IMAGE_TILED_MAGMA = new ResourceLocation(MasteryMod.MOD_ID,
            "textures/gui/tiled/magma.png");
    // Prismarine
    ResourceLocation ANIMATED_IMAGE_TILED_PRISMARINE = new ResourceLocation(MasteryMod.MOD_ID,
            "textures/gui/tiled/prismarine.png");
    // Sea lantern
    ResourceLocation ANIMATED_IMAGE_TILED_SEA_LANTERN = new ResourceLocation(MasteryMod.MOD_ID,
            "textures/gui/tiled/sea_lantern.png");

}
