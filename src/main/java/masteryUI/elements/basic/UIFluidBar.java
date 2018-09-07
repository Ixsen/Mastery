package masteryUI.elements.basic;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import masteryUI.colors.UIColors;
import masteryUI.elements.core.UIContainer;
import masteryUI.elements.core.UIImageManager;
import masteryUI.elements.core.UIRenderUtils;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Subaro
 */
public class UIFluidBar extends UIFluid {

    private ReadableColor gaugeTintColor = ReadableColor.WHITE;

    public UIFluidBar(FluidStack fluidStack) {
        super(fluidStack);
    }

    public UIFluidBar(UIContainer parentContainer, FluidStack fluidStack) {
        super(parentContainer, fluidStack);
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        super.draw(parentX, parentY, mouseX, mouseY, partialTicks);
        this.startScaling(this.getScale());
        {
            Point gPos = this.getGlobalPosition(parentX, parentY);
            UIRenderUtils.setColorRGB(UIColors.toInt(this.gaugeTintColor));
            // Draw container with gauge
            this.drawImageBind(UIImageManager.UI_FLUID_BAR_CONTAINER, gPos.getX(), gPos.getY(),
                    this.getMinimumSize().getWidth(), this.getMaximumSize().getHeight());
        }
        this.endScaling();
    }

    public ReadableColor getGaugeTintColor() {
        return this.gaugeTintColor;
    }

    public void setGaugeTintColor(ReadableColor gaugeTintColor) {
        this.gaugeTintColor = gaugeTintColor;
    }
}
