package masteryUI.elements.basic;

import org.lwjgl.util.Point;

import masteryUI.elements.core.UIContainer;
import masteryUI.elements.core.UIRenderUtils;
import masteryUI.elements.core.UIScalableElement;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Subaro
 */
public class UIFluid extends UIScalableElement {

    /** the fluid to render */
    private FluidStack fluidStack;
    /** indicated if the rendered fluid should be flowing */
    private boolean flowing;

    public UIFluid(FluidStack fluidStack) {
        super(1f);
        this.fluidStack = fluidStack;
    }

    public UIFluid(UIContainer parentContainer, FluidStack fluidStack) {
        super(parentContainer, 1f);
        this.fluidStack = fluidStack;
    }

    public void setFluidStack(FluidStack fluidStack) {
        this.fluidStack = fluidStack;
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        if (this.fluidStack == null) {
            return;
        }
        this.startScaling(this.getScale());
        {
            this.drawBackground(parentX, parentY, mouseX, mouseY, partialTicks);

            Point gPos = this.getGlobalPosition(parentX, parentY);
            float quotient = this.fluidStack.amount / 100f;
            int barHeight = (int) (this.getMinimumSize().getHeight() * quotient);
            int yOffset = this.getMinimumSize().getHeight() - barHeight;
            if (this.flowing) {
                UIRenderUtils.renderTiledFluidFlowing(gPos.getX(), gPos.getY() + yOffset,
                        this.getMinimumSize().getWidth(),
                        barHeight, this.zLevel, this.fluidStack);
            } else {
                UIRenderUtils.renderTiledFluidStill(gPos.getX(), gPos.getY() + yOffset,
                        this.getMinimumSize().getWidth(),
                        barHeight, this.zLevel, this.fluidStack);
            }
        }
        this.endScaling();
    }

    public void setFillAmount(int percent) {
        if (percent <= 0) {
            percent = 0;
        } else if (percent >= 100) {
            percent = 100;
        }
        this.fluidStack.amount = percent;
    }

    public int getFillAmount() {
        return this.fluidStack.amount;
    }

    public boolean isFlowing() {
        return this.flowing;
    }

    public void setFlowing(boolean flowing) {
        this.flowing = flowing;
    }

}
