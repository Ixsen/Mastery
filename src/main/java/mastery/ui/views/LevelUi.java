package mastery.ui.views;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import masteryUI.elements.basic.UIKeyTest;
import masteryUI.elements.basic.UILabel;
import masteryUI.elements.basic.UILabel.UILabelAlignment;
import masteryUI.elements.core.UIMCScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends UIMCScreen {

    public LevelUi() {
        super();
    }

    @Override
    public void initGui() {
        UILabel label = new UILabel("WOOW", ReadableColor.PURPLE, 1, UILabelAlignment.MIDDLE_CENTER);
        label.setSize(100, 11);
        label.setBackgroundColor(ReadableColor.BLACK);

        UIKeyTest item = new UIKeyTest("KEY", ReadableColor.ORANGE, 1, UILabelAlignment.MIDDLE_CENTER);
        item.setPosition(new Point(10, 10));
        item.setSize(100, 20);
        item.setBackgroundColor(ReadableColor.GREY);
        item.addChangeListener((e) -> this.mc.player.sendChatMessage("New Value: " + e.getNewValue()));
        item.setTooltip(label);
        this.screenContainer.addElement(item);

        // Always call super
        super.initGui();
    }
}
