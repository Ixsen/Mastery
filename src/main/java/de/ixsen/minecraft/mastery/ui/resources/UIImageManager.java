package de.ixsen.minecraft.mastery.ui.resources;

import de.ixsen.minecraft.uilib.elements.core.data.UIImageData;

/**
 * @author Subaro
 */
public interface UIImageManager {

    // Smiley Image
    UIImageData SMILEY = new UIImageData(UIResourceLocationManager.WIDGETS_ATLAS, 0, 80, 23, 21, 512, 512);

    // UI Fluid Bar Gauge
    UIImageData UI_FLUID_BAR_CONTAINER = new UIImageData(UIResourceLocationManager.WIDGETS_ATLAS, 200, 0, 18, 49, 512,
            512);

    // Tiled Background
    UIImageData IMAGE_TILED_OAK = new UIImageData(UIResourceLocationManager.IMAGE_TILED_OAK, 0, 0, 16, 16, 16, 16);
}
