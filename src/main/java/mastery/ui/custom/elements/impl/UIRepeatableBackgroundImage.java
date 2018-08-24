package mastery.ui.custom.elements.impl;
/**
 * @author Subaro
 */

import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import mastery.capability.skillclasses.MasterySpec;
import mastery.resource.UIBackgroundUtils;
import mastery.ui.custom.elements.abstracts.AbstractUIImage;
import net.minecraft.util.ResourceLocation;

public class UIRepeatableBackgroundImage extends AbstractUIImage<UIRepeatableBackgroundImage> {

    private MasterySpec mastery;

    public UIRepeatableBackgroundImage(MasterySpec mastery) {
        this.mastery = mastery;
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
        return UIBackgroundUtils.getMasteryRepeatableBackground(this.mastery);
    }
}
