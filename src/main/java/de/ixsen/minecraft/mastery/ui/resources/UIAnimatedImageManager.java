package de.ixsen.minecraft.mastery.ui.resources;

import de.ixsen.minecraft.uilib.elements.core.data.UIAnimatedImageData;

/**
 * @author Subaro
 */
public interface UIAnimatedImageManager {

    // EXP FLUID
    UIAnimatedImageData FLUID_EXP_STILL = new UIAnimatedImageData(UIResourceLocationManager.FLUID_EXP_STILL, 0, 0, 16,
            16, 16, 512);
    UIAnimatedImageData FLUID_EXP_FLOWING = new UIAnimatedImageData(UIResourceLocationManager.FLUID_EXP_STILL, 0, 0, 16,
            16, 16, 512);

    // LAVA FLUID
    UIAnimatedImageData FLUID_LAVA_STILL = new UIAnimatedImageData(UIResourceLocationManager.FLUID_LAVA_STILL, 0, 0, 16,
            16, 16, 320);
    UIAnimatedImageData FLUID_LAVA_FLOWING = new UIAnimatedImageData(UIResourceLocationManager.FLUID_LAVA_FLOWING, 0, 0,
            32, 32, 32, 512);
    // WATER FLUID
    UIAnimatedImageData FLUID_WATER_STILL = new UIAnimatedImageData(UIResourceLocationManager.FLUID_WATER_STILL, 0, 0,
            16, 16, 16, 512);
    UIAnimatedImageData FLUID_WATER_FLOWING = new UIAnimatedImageData(UIResourceLocationManager.FLUID_WATER_FLOWING, 0,
            0, 32, 32, 32, 1024);

    // Tiled Backgrounds
    UIAnimatedImageData ANIMATED_IMAGE_TILED_MAGMA = new UIAnimatedImageData(
            UIResourceLocationManager.ANIMATED_IMAGE_TILED_MAGMA, 0, 0, 16, 16, 16, 48);
    UIAnimatedImageData ANIMATED_IMAGE_TILED_PRISMARINE = new UIAnimatedImageData(
            UIResourceLocationManager.ANIMATED_IMAGE_TILED_PRISMARINE, 0, 0, 16, 16, 16, 64);
    UIAnimatedImageData ANIMATED_IMAGE_TILED_SEA_LANTERN = new UIAnimatedImageData(
            UIResourceLocationManager.ANIMATED_IMAGE_TILED_SEA_LANTERN, 0, 0, 16, 16, 16, 80);
}
