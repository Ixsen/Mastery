package de.ixsen.minecraft.mastery.ui.resources;

import de.ixsen.minecraft.uilib.elements.core.data.AnimatedImageData;

/**
 * @author Subaro
 */
public interface UIAnimatedImageManager {

    // EXP FLUID
    AnimatedImageData FLUID_EXP_STILL = new AnimatedImageData(UIResourceLocationManager.FLUID_EXP_STILL, 0, 0, 16,
            16, 16, 512);
    AnimatedImageData FLUID_EXP_FLOWING = new AnimatedImageData(UIResourceLocationManager.FLUID_EXP_STILL, 0, 0, 16,
            16, 16, 512);

    // LAVA FLUID
    AnimatedImageData FLUID_LAVA_STILL = new AnimatedImageData(UIResourceLocationManager.FLUID_LAVA_STILL, 0, 0, 16,
            16, 16, 320);
    AnimatedImageData FLUID_LAVA_FLOWING = new AnimatedImageData(UIResourceLocationManager.FLUID_LAVA_FLOWING, 0, 0,
            32, 32, 32, 512);
    // WATER FLUID
    AnimatedImageData FLUID_WATER_STILL = new AnimatedImageData(UIResourceLocationManager.FLUID_WATER_STILL, 0, 0,
            16, 16, 16, 512);
    AnimatedImageData FLUID_WATER_FLOWING = new AnimatedImageData(UIResourceLocationManager.FLUID_WATER_FLOWING, 0,
            0, 32, 32, 32, 1024);

    // Tiled Backgrounds
    AnimatedImageData ANIMATED_IMAGE_TILED_MAGMA = new AnimatedImageData(
            UIResourceLocationManager.ANIMATED_IMAGE_TILED_MAGMA, 0, 0, 16, 16, 16, 48);
    AnimatedImageData ANIMATED_IMAGE_TILED_PRISMARINE = new AnimatedImageData(
            UIResourceLocationManager.ANIMATED_IMAGE_TILED_PRISMARINE, 0, 0, 16, 16, 16, 64);
    AnimatedImageData ANIMATED_IMAGE_TILED_SEA_LANTERN = new AnimatedImageData(
            UIResourceLocationManager.ANIMATED_IMAGE_TILED_SEA_LANTERN, 0, 0, 16, 16, 16, 80);
}
