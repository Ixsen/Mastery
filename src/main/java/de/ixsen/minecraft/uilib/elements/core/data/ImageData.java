package de.ixsen.minecraft.uilib.elements.core.data;

import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class ImageData {

    private ResourceLocation location;
    private int u;
    private int v;
    private int uWidth;
    private int vHeight;
    private int textureWidth;
    private int textureHeight;

    public ImageData(ResourceLocation location, int u, int v, int uWidth, int vHeight, int textureWidth,
            int textureHeight) {
        this.location = location;
        this.u = u;
        this.v = v;
        this.uWidth = uWidth;
        this.vHeight = vHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public ResourceLocation getLocation() {
        return this.location;
    }

    public void setLocation(ResourceLocation location) {
        this.location = location;
    }

    public int getU() {
        return this.u;
    }

    public int getV() {
        return this.v;
    }

    public int getuWidth() {
        return this.uWidth;
    }

    public int getvHeight() {
        return this.vHeight;
    }

    public int getTextureWidth() {
        return this.textureWidth;
    }

    public int getTextureHeight() {
        return this.textureHeight;
    }
}
