package de.ixsen.minecraft.uilib.elements;

import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.mastery.ui.resources.UIImageManager;
import de.ixsen.minecraft.uilib.common.ColorUtils;
import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Subaro
 */
public class FluidBar extends Fluid {

    private ReadableColor gaugeTintColor = ReadableColor.WHITE;

    public FluidBar(FluidStack fluidStack) {
        super(fluidStack);
    }

    public FluidBar(UiContainer parentContainer, FluidStack fluidStack) {
        super(parentContainer, fluidStack);
    }

    @Override
    public void drawForeground(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        this.setColorRGB(ColorUtils.toInt(this.gaugeTintColor));
        // Draw container with gauge
        this.drawImageBind(UIImageManager.UI_FLUID_BAR_CONTAINER, getGlobalPosition().getX(),
                getGlobalPosition().getY(), this.getMinimumSize().getWidth(), this.getMaximumSize().getHeight());
    }

    public ReadableColor getGaugeTintColor() {
        return this.gaugeTintColor;
    }

    public void setGaugeTintColor(ReadableColor gaugeTintColor) {
        this.gaugeTintColor = gaugeTintColor;
    }
}
