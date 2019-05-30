package de.ixsen.minecraft.mastery.ui.views;

import de.ixsen.minecraft.uilib.elements.basic.*;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.mastery.common.annotations.SubscribeToClientEventBus;
import de.ixsen.minecraft.mastery.ui.resources.UIAnimatedImageManager;
import de.ixsen.minecraft.mastery.ui.resources.UIImageManager;
import de.ixsen.minecraft.uilib.elements.basic.UIAlignment;
import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.elements.core.UIMCScreen;
import de.ixsen.minecraft.uilib.layout.FreeFormLayout;
import de.ixsen.minecraft.uilib.layout.UILayout;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SubscribeToClientEventBus
@SideOnly(Side.CLIENT)
public class ExampleUI extends UIMCScreen {

    public ExampleUI() {
        super();
    }

    @Override
    protected UILayout createLayout() {
        return new FreeFormLayout();
    }

    @Override
    protected UIContainer createScreenContainer() {
        UIContainer container = new UIContainer(this);

        // Tiled animated image
        UITiledAnimatedImage tiledAnimated = new UITiledAnimatedImage(UIAnimatedImageManager.FLUID_EXP_STILL, 6);
        tiledAnimated.setPosition(new Point(200, 80));
        tiledAnimated.setSize(80, 5);
        container.addElement(tiledAnimated);

        // ExampleTiledImage
        UITiledImage tiled = new UITiledImage(UIImageManager.IMAGE_TILED_OAK);
        tiled.setPosition(new Point(200, 50));
        tiled.setSize(20, 20);
        container.addElement(tiled);

        // Example animated button
        UIAnimatedImage image = new UIAnimatedImage(UIAnimatedImageManager.FLUID_EXP_FLOWING, 12);
        image.setPosition(new Point(200, 10));
        image.setSize(80, 16);
        container.addElement(image);

        // Example Image Button
        UIImageButton button = new UIImageButton(UIImageManager.SMILEY);
        button.setPosition(new Point(0, 0));
        button.setSize(30, 30);
        button.addClickListener((e) -> this.mc.player.sendChatMessage("Button Image"));
        container.addElement(button);

        // Just a Button having the image button as tooltip
        UIButton buttonText = new UIButton("WoooW");
        buttonText.setTooltip(button);
        buttonText.setPosition(new Point(50, 10));
        buttonText.setSize(30, 30);
        buttonText.addClickListener((e) -> this.mc.player.sendChatMessage("Button Text"));
        container.addElement(buttonText);

        // A Text field
        UITextField field = new UITextField("", "Insert...", ReadableColor.WHITE, 1, UIAlignment.MIDDLE_CENTER);
        field.setSize(100, 16);
        field.setPosition(new Point(90, 10));
        field.setBackgroundColor(ReadableColor.DKGREY);
        container.addElement(field);

        // An item rendered into the gui
        UIItem item = new UIItem(1, new ItemStack(Items.BEEF), false);
        item.setPosition(new Point(90, 30));
        item.setBackgroundColor(ReadableColor.DKGREY);
        container.addElement(item);

        // Used to display any kind of fluid freely. Fluid can stand still or flow.
        UIFluid barLava = new UIFluid(new FluidStack(FluidRegistry.LAVA, 100));
        barLava.setScale(1);
        barLava.setSize(10, 90);
        barLava.setFlowing(true);
        barLava.setPosition(new Point(10, 50));
        container.addElement(barLava);

        // Used to display any kind of fluid inside a container with indicating levels
        // UIFluidBar barWater = new UIFluidBar(new FluidStack(FluidRegistry.WATER, 100));
        // barWater.setScale(1);
        // barWater.setBackgroundColor(ReadableColor.LTGREY);
        // barWater.setSize(20, 60);
        // barWater.setPosition(new Point(25, 50));
        // screenContainer.addElement(barWater);
        //
        // // Button to reduce the amount of 'Water'
        // UIButton buttonWaterMinus = new UIButton("-");
        // buttonWaterMinus.setPosition(new Point(45, 50));
        // buttonWaterMinus.setSize(15, 15);
        // buttonWaterMinus.addClickListener((e) -> barWater.setFillAmount(barWater.getFillAmount() - 5));
        // screenContainer.addElement(buttonWaterMinus);
        //
        // // Button to increase the amount of 'Water'
        // UIButton buttonWaterPlus = new UIButton("+");
        // buttonWaterPlus.setPosition(new Point(45, 65));
        // buttonWaterPlus.setSize(15, 15);
        // buttonWaterPlus.addClickListener((e) -> barWater.setFillAmount(barWater.getFillAmount() + 5));
        // screenContainer.addElement(buttonWaterPlus);


        UILabel abc = new UILabel("ABC", ReadableColor.YELLOW, 1F, UIAlignment.MIDDLE_CENTER);
        container.addElement(abc);

        return container;
    }
}
