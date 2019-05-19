package de.ixsen.minecraft.uilib.elements.core.data;

import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UIAnimatedImageData {
    private ResourceLocation location;
    private int u;
    private int v;
    private int uWidth;
    private int vHeight;
    private int textureWidth;
    private int textureHeight;
    private int frames;
    private int tickSpeed;
    private UIImageData[] data;
    private int currentFrame;
    private int currentTick;

    public UIAnimatedImageData(ResourceLocation location, int u, int v, int uWidth, int vHeight, int textureWidth,
            int textureHeight) {
        this.location = location;
        this.u = u;
        this.v = v;
        this.uWidth = uWidth;
        this.vHeight = vHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.frames = textureHeight / vHeight;
        this.currentFrame = 0;
        this.currentTick = 0;
        this.tickSpeed = 100;

        // init data
        this.data = new UIImageData[this.frames];
        for (int i = 0; i < this.frames; i++) {
            this.data[i] = new UIImageData(location, u, v + i * vHeight, uWidth, vHeight, textureWidth, textureHeight);
        }
    }

    public ResourceLocation getLocation() {
        return this.location;
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

    public int getFrames() {
        return this.frames;
    }

    public UIImageData getNextImage(int tickAmount) {
        UIImageData imageData = this.data[this.currentFrame];
        this.currentTick += tickAmount;
        if (this.currentTick >= this.tickSpeed) {
            this.currentTick = 0;
            this.currentFrame++;
            if (this.currentFrame == this.frames) {
                this.currentFrame = 0;
            }
        }
        return imageData;
    }
}
