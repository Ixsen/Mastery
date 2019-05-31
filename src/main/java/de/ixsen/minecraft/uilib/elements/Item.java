package de.ixsen.minecraft.uilib.elements;

import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import de.ixsen.minecraft.uilib.elements.core.ScalableGuiElement;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import net.minecraft.item.ItemStack;

/**
 * @author Subaro
 */
public class Item extends ScalableGuiElement {

    private ItemStack item;
    private boolean renderEffect = false;

    public Item(float scale, ItemStack item, boolean renderEffect) {
        super(scale);
        this.item = item;
        this.renderEffect = renderEffect;
    }

    public Item(GuiContainer parentContainer, float scale, ItemStack item, boolean renderEffect) {
        super(parentContainer, scale);
        this.item = item;
        this.renderEffect = renderEffect;
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        Point gPos = this.getGlobalPosition(parentX, parentY);
        if (this.renderEffect) {
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(this.item, gPos.getX(), gPos.getY());
        } else {
            this.mc.getRenderItem().renderItemIntoGUI(this.item, gPos.getX(), gPos.getY());
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
