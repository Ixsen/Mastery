package mastery.ui.custom.elements.impl;
/**
 * @author Subaro
 */

import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import mastery.ui.custom.elements.abstracts.AbstractUIImage;
import net.minecraft.util.ResourceLocation;

public class UIRepeatableBackgroundImage extends AbstractUIImage<UIRepeatableBackgroundImage> {

    private static final ResourceLocation BACKGROUND_ADV = new ResourceLocation(
            "textures/gui/advancements/backgrounds/adventure.png");
    private static final ResourceLocation BACKGROUND_END = new ResourceLocation(
            "textures/gui/advancements/backgrounds/end.png");
    private static final ResourceLocation BACKGROUND_HUSBANDRY = new ResourceLocation(
            "textures/gui/advancements/backgrounds/husbandry.png");
    private static final ResourceLocation BACKGROUND_NETHER = new ResourceLocation(
            "textures/gui/advancements/backgrounds/nether.png");
    private static final ResourceLocation BACKGROUND_STONE = new ResourceLocation(
            "textures/gui/advancements/backgrounds/stone.png");

    private UIRepeatableBackgroundImageTypes type;

    public enum UIRepeatableBackgroundImageTypes {
        ADVENTURE, ENDER, HUSBANDRY, NETHER, STONE
    }

    public UIRepeatableBackgroundImage(UIRepeatableBackgroundImageTypes type) {
        this.type = type;
        this.resourceLocation = this.getLocation();
    }

    @Override
    protected UIRepeatableBackgroundImage getThis() {
        return this;
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        if (this.texture != null) {
            renderer.bindTexture(this.texture);
        } else {
            renderer.bindTexture(this.resourceLocation);
        }

        int numberX = Math.round(size.getWidth() / this.textureWidth);
        int numberY = Math.round(size.getHeight() / this.textureHeight);

        for (int x = 0; x <= numberX; x++) {
            for (int y = 0; y <= numberY; y++) {
                int xPos = this.textureWidth * x;
                int yPos = this.textureHeight * y;
                renderer.drawTexturedRect(xPos, yPos, this.u, this.v,
                        this.textureWidth, this.textureHeight, this.uWidth, this.vHeight, this.textureWidth,
                        this.textureHeight);

            }
        }
    }

    private ResourceLocation getLocation() {
        switch (this.type) {
        case ADVENTURE:
            return BACKGROUND_ADV;
        case ENDER:
            return BACKGROUND_END;
        case HUSBANDRY:
            return BACKGROUND_HUSBANDRY;
        case NETHER:
            return BACKGROUND_NETHER;
        case STONE:
        default:
            return BACKGROUND_STONE;
        }
    }
}
