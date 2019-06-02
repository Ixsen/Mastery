package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.ReadableDimension;

import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.ScalableUiElement;
import net.minecraft.item.ItemStack;

/**
 * @author Subaro
 */
public class Item extends ScalableUiElement {

    private ItemStack item;
    private boolean renderEffect = false;

    public Item(float scale, ItemStack item, boolean renderEffect) {
        super(scale);
        this.item = item;
        this.renderEffect = renderEffect;
    }

    public Item(UiContainer parentContainer, float scale, ItemStack item, boolean renderEffect) {
        super(parentContainer, scale);
        this.item = item;
        this.renderEffect = renderEffect;
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.renderEffect) {
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(this.item, this.getGlobalPosition().getX(),
                    this.getGlobalPosition().getY());
        } else {
            this.mc.getRenderItem().renderItemIntoGUI(this.item, this.getGlobalPosition().getX(),
                    this.getGlobalPosition().getY());
        }
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
