package de.ixsen.minecraft.mastery.ui.views;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.mastery.common.annotations.SubscribeToClientEventBus;
import de.ixsen.minecraft.mastery.ui.resources.UIAnimatedImageManager;
import de.ixsen.minecraft.mastery.ui.resources.UIImageManager;
import de.ixsen.minecraft.uilib.common.Alignment;
import de.ixsen.minecraft.uilib.elements.*;
import de.ixsen.minecraft.uilib.elements.container.FreeFormContainer;
import de.ixsen.minecraft.uilib.elements.container.UiContainer;
import de.ixsen.minecraft.uilib.elements.core.UiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SubscribeToClientEventBus
@SideOnly(Side.CLIENT)
public class ExampleUI extends UiScreen {

    public ExampleUI() {
        super();
    }

    @Override
    protected UiContainer createScreenContainer() {
        UiContainer container = new FreeFormContainer(this);

        // Tiled animated image
        TiledAnimatedImage tiledAnimated = new TiledAnimatedImage(UIAnimatedImageManager.FLUID_EXP_STILL, 6);
        tiledAnimated.setRelativePosition(new Point(200, 80));
        tiledAnimated.setSize(80, 5);
        container.addElements(tiledAnimated);

        // ExampleTiledImage
        TiledImage tiled = new TiledImage(UIImageManager.IMAGE_TILED_OAK);
        tiled.setRelativePosition(new Point(200, 50));
        tiled.setSize(20, 20);
        container.addElements(tiled);

        // Example animated button
        AnimatedImage image = new AnimatedImage(UIAnimatedImageManager.FLUID_EXP_FLOWING, 12);
        image.setRelativePosition(new Point(200, 10));
        image.setSize(80, 16);
        container.addElements(image);

        // Example Image Button
        ImageButton button = new ImageButton(UIImageManager.SMILEY);
        button.setRelativePosition(new Point(0, 0));
        button.setSize(30, 30);
        button.addClickListener((e) -> this.mc.player.sendChatMessage("Button Image"));
        container.addElements(button);

        // Just a Button having the image button as tooltip
        Button buttonText = new Button("WoooW");
        buttonText.setTooltip(button);
        buttonText.setRelativePosition(new Point(50, 10));
        buttonText.setSize(30, 30);
        buttonText.addClickListener((e) -> this.mc.player.sendChatMessage("Button Text"));
        container.addElements(buttonText);

        // A Text field
        TextField field = new TextField("", "Insert...", ReadableColor.WHITE, 1, Alignment.MIDDLE_CENTER);
        field.setSize(100, 16);
        field.setRelativePosition(new Point(90, 10));
        field.setBackgroundColor(ReadableColor.DKGREY);
        container.addElements(field);

        // An item rendered into the gui
        Item item = new Item(1, new ItemStack(Items.BEEF), false);
        item.setRelativePosition(new Point(90, 30));
        item.setBackgroundColor(ReadableColor.DKGREY);
        container.addElements(item);

        // Used to display any kind of fluid freely. Fluid can stand still or flow.
        Fluid barLava = new Fluid(new FluidStack(FluidRegistry.LAVA, 100));
        barLava.setScale(1);
        barLava.setSize(10, 90);
        barLava.setFlowing(true);
        barLava.setRelativePosition(new Point(10, 50));
        container.addElements(barLava);

        // Used to display any kind of fluid inside a container with indicating levels
        // FluidBar barWater = new FluidBar(new FluidStack(FluidRegistry.WATER, 100));
        // barWater.setScale(1);
        // barWater.setBackgroundColor(ReadableColor.LTGREY);
        // barWater.setSize(20, 60);
        // barWater.setRelativePosition(new Point(25, 50));
        // screenContainer.addElements(barWater);
        //
        // // Button to reduce the amount of 'Water'
        // Button buttonWaterMinus = new Button("-");
        // buttonWaterMinus.setRelativePosition(new Point(45, 50));
        // buttonWaterMinus.setSize(15, 15);
        // buttonWaterMinus.addClickListener((e) -> barWater.setFillAmount(barWater.getFillAmount() - 5));
        // screenContainer.addElements(buttonWaterMinus);
        //
        // // Button to increase the amount of 'Water'
        // Button buttonWaterPlus = new Button("+");
        // buttonWaterPlus.setRelativePosition(new Point(45, 65));
        // buttonWaterPlus.setSize(15, 15);
        // buttonWaterPlus.addClickListener((e) -> barWater.setFillAmount(barWater.getFillAmount() + 5));
        // screenContainer.addElements(buttonWaterPlus);

        return container;
    }
}
