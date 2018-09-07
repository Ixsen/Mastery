package mastery.ui.views;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import mastery.MasteryMod;
import masteryUI.elements.basic.UIButton;
import masteryUI.elements.basic.UIFluid;
import masteryUI.elements.basic.UIFluidBar;
import masteryUI.elements.basic.UIImageButton;
import masteryUI.elements.basic.UIItem;
import masteryUI.elements.basic.UILabel.UIAlignment;
import masteryUI.elements.basic.UITextField;
import masteryUI.elements.core.UIImageData;
import masteryUI.elements.core.UIMCScreen;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends UIMCScreen {

    protected static final ResourceLocation WIDGETS_ATLAS = new ResourceLocation(MasteryMod.modid,
            "textures/gui/widgets.png");

    public LevelUi() {
        super();
    }

    @Override
    public void initGui() {
        this.initExampleScene();

        // Always call super
        super.initGui();
    }

    private void initExampleScene() {
        // Example Image Button
        UIImageData smiley = new UIImageData(WIDGETS_ATLAS, 0, 80, 23, 21, 512, 512);
        UIImageButton button = new UIImageButton(smiley);
        button.setPosition(new Point(0, 0));
        button.setSize(30, 30);
        button.addClickListener((e) -> this.mc.player.sendChatMessage("Button Image"));
        this.screenContainer.addElement(button);

        // Just a Button having the image button as tooltip
        UIButton buttonText = new UIButton("WoooW");
        buttonText.setTooltip(button);
        buttonText.setPosition(new Point(50, 10));
        buttonText.setSize(30, 30);
        buttonText.addClickListener((e) -> this.mc.player.sendChatMessage("Button Text"));
        this.screenContainer.addElement(buttonText);

        // A Text field
        UITextField field = new UITextField("", "Insert...", ReadableColor.WHITE, 1, UIAlignment.MIDDLE_CENTER);
        field.setSize(100, 16);
        field.setPosition(new Point(90, 10));
        field.setBackgroundColor(ReadableColor.DKGREY);
        this.screenContainer.addElement(field);

        // An item rendered into the gui
        UIItem item = new UIItem(1, new ItemStack(Items.BEEF), false);
        item.setPosition(new Point(90, 30));
        item.setBackgroundColor(ReadableColor.DKGREY);
        this.screenContainer.addElement(item);

        // Used to display any kind of fluid freely. Fluid can stand still or flow.
        UIFluid barLava = new UIFluid(new FluidStack(FluidRegistry.LAVA, 100));
        barLava.setScale(1);
        barLava.setSize(10, 90);
        barLava.setFlowing(true);
        barLava.setPosition(new Point(10, 50));
        this.screenContainer.addElement(barLava);

        // Used to display any kind of fluid inside a container with indicating levels
        UIFluidBar barWater = new UIFluidBar(new FluidStack(FluidRegistry.WATER, 100));
        barWater.setScale(1);
        barWater.setBackgroundColor(ReadableColor.LTGREY);
        barWater.setSize(20, 60);
        barWater.setPosition(new Point(25, 50));
        this.screenContainer.addElement(barWater);

        // Button to reduce the amount of 'Water'
        UIButton buttonWaterMinus = new UIButton("-");
        buttonWaterMinus.setPosition(new Point(45, 50));
        buttonWaterMinus.setSize(15, 15);
        buttonWaterMinus.addClickListener((e) -> barWater.setFillAmount(barWater.getFillAmount() - 5));
        this.screenContainer.addElement(buttonWaterMinus);

        // Button to increase the amount of 'Water'
        UIButton buttonWaterPlus = new UIButton("+");
        buttonWaterPlus.setPosition(new Point(45, 65));
        buttonWaterPlus.setSize(15, 15);
        buttonWaterPlus.addClickListener((e) -> barWater.setFillAmount(barWater.getFillAmount() + 5));
        this.screenContainer.addElement(buttonWaterPlus);
    }
}
