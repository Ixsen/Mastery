package mastery.ui.custom.elements.interfaces;

import java.awt.image.BufferedImage;

import de.johni0702.minecraft.gui.element.GuiElement;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public interface IUISlot<T extends IUISlot<T>> extends GuiElement<T> {
    T setTexture(BufferedImage img);

    T setTexture(ResourceLocation resourceLocation);

    T setUV(int u, int v);

    T setUVSize(int width, int height);

    T setTextureSize(int textureWidth, int textureHeight);

    T setActiveUV(int activeU, int activeV);

    IUISlotTabGroup getSlotGroup();
}
