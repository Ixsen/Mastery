package de.ixsen.minecraft.uilib.elements.basic;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.elements.core.UIScalableElement;
import net.minecraft.item.ItemStack;

/**
 * @author Subaro
 */
public class UIItem extends UIScalableElement {

    private ItemStack item;
    private boolean renderEffect = false;

    public UIItem(float scale, ItemStack item, boolean renderEffect) {
        super(scale);
        this.item = item;
        this.renderEffect = renderEffect;
    }

    public UIItem(UIContainer parentContainer, float scale, ItemStack item, boolean renderEffect) {
        super(parentContainer, scale);
        this.item = item;
        this.renderEffect = renderEffect;
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.startScaling(this.getScale());
        {
            // Draw Background
            super.draw(parentX, parentY, mouseX, mouseY, partialTicks);

            // Draw Item into GUI
            Point gPos = this.getGlobalPosition(parentX, parentY);
            if (this.renderEffect) {
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(this.item, gPos.getX(), gPos.getY());
            } else {
                this.mc.getRenderItem().renderItemIntoGUI(this.item, gPos.getX(), gPos.getY());
            }
        }
        this.endScaling();
    }

    @Override
    public ReadableDimension getMinimumSize() {
        return new Dimension(17, 17);
    }

    @Override
    public ReadableDimension getMaximumSize() {
        return new Dimension(17, 17);
    }

}
