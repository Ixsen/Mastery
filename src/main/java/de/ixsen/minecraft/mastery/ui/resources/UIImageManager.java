package de.ixsen.minecraft.mastery.ui.resources;

import de.ixsen.minecraft.uilib.elements.core.data.ImageData;

/**
 * @author Subaro
 */
public interface UIImageManager {

    // Smiley Image
    ImageData SMILEY = new ImageData(UIResourceLocationManager.WIDGETS_ATLAS, 0, 80, 23, 21, 512, 512);

    // UI Fluid Bar Gauge
    ImageData UI_FLUID_BAR_CONTAINER = new ImageData(UIResourceLocationManager.WIDGETS_ATLAS, 200, 0, 18, 49, 512,
            512);

    // Tiled Background
    ImageData IMAGE_TILED_OAK = new ImageData(UIResourceLocationManager.IMAGE_TILED_OAK, 0, 0, 16, 16, 16, 16);
}
