package mastery.ui.views;

import org.lwjgl.util.Point;

import mastery.MasteryMod;
import masteryUI.elements.basic.UIButton;
import masteryUI.elements.basic.UIImageButton;
import masteryUI.elements.core.UIImageData;
import masteryUI.elements.core.UIMCScreen;
import net.minecraft.util.ResourceLocation;
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
        UIImageData smiley = new UIImageData(WIDGETS_ATLAS, 0, 80, 23, 21, 512, 512);

        UIImageButton button = new UIImageButton(smiley);
        button.setPosition(new Point(10, 10));
        button.setSize(60, 30);
        button.addClickListener((e) -> this.mc.player.sendChatMessage("Button Image"));
        this.screenContainer.addElement(button);

        UIButton buttonText = new UIButton("WoooW");
        buttonText.setTooltip(button);
        buttonText.setPosition(new Point(10, 50));
        buttonText.setSize(200, 40);
        buttonText.addClickListener((e) -> this.mc.player.sendChatMessage("Button Text"));
        this.screenContainer.addElement(buttonText);

        // Always call super
        super.initGui();
    }
}
