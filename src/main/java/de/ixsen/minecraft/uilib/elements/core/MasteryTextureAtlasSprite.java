package de.ixsen.minecraft.uilib.elements.core;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;

/**
 * @author Subaro
 */
public class MasteryTextureAtlasSprite extends TextureAtlasSprite {

    public MasteryTextureAtlasSprite(String iconName, int iconWidth, int iconHeight, int frames, boolean rotate) {
        super(iconName);
        this.setIconHeight(iconHeight);
        this.setIconWidth(iconWidth);
        this.frameCounter = frames;
        this.initSprite(0, 0, 0, 0, rotate);
    }
}
