package mastery.ui.views;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import masteryUI.elements.basic.UIItem;
import masteryUI.elements.basic.UIKeyTest;
import masteryUI.elements.basic.UILabel;
import masteryUI.elements.basic.UILabel.UILabelAlignment;
import masteryUI.elements.core.UIMCScreen;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends UIMCScreen {

    public LevelUi() {
        super();
    }

    @Override
    public void initGui() {
        UIKeyTest item = new UIKeyTest("KEY", ReadableColor.ORANGE, 1, UILabelAlignment.MIDDLE_CENTER);
        item.setPosition(new Point(10, 10));
        item.setSize(100, 20);
        item.setBackgroundColor(ReadableColor.GREY);
        item.addTypeListener((e) -> item.setText(e.getTypedChar() + " | " + e.getKeyCode()));
        this.screenContainer.addElement(item);

        UIItem item2 = new UIItem(2, new ItemStack(Items.ENDER_EYE), true);
        item2.setPosition(new Point(10, 50));
        item2.setBackgroundColor(ReadableColor.GREY);
        this.screenContainer.addElement(item2);

        UILabel label = new UILabel("Test", ReadableColor.PURPLE, 2, UILabelAlignment.MIDDLE_CENTER);
        label.setPosition(new Point(10, 70));
        label.setSize(200, 11);
        label.setBackgroundColor(ReadableColor.GREY);
        label.addClickListener((e) -> this.mc.player.sendChatMessage("Clicked: Test"));
        label.addReleaseListener((e) -> this.mc.player.sendChatMessage("Released: Test"));
        label.addDragListener((e) -> label.setPosition(e.getMousePosition()));
        this.screenContainer.addElement(label);

        UILabel label2 = new UILabel("WOW", ReadableColor.PURPLE, 2, UILabelAlignment.MIDDLE_CENTER);
        label2.setPosition(new Point(10, 95));
        label2.setSize(200, 11);
        label2.setBackgroundColor(ReadableColor.GREY);
        label2.addClickListener((e) -> this.mc.player.sendChatMessage("Clicked: WOW"));
        label2.addReleaseListener((e) -> this.mc.player.sendChatMessage("Released: WOW"));
        label2.addDragListener((e) -> label2.setPosition(e.getMousePosition()));
        this.screenContainer.addElement(label2);

        // Always call super
        super.initGui();
    }
}
