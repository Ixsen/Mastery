package mastery.ui.custom.elements.interfaces;

import java.awt.image.BufferedImage;

import de.johni0702.minecraft.gui.element.GuiElement;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public interface IUIImage<T extends IUIImage<T>> extends GuiElement<T> {
    T setTexture(BufferedImage img);

    T setTexture(ResourceLocation resourceLocation, int textureWidth, int textureHeight);

    T setTexture(ResourceLocation resourceLocation, int textureWidth, int textureHeight, int u, int v, int width,
            int height);

    T setU(int u);

    T setV(int v);

    T setUV(int u, int v);

    T setUWidth(int width);

    T setVHeight(int height);

    T setUVSize(int width, int height);

    T setTextureSize(int textureWidth, int textureHeight);
}
