package mastery.ui.views;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import masteryUI.elements.basic.UILabel.UILabelAlignment;
import masteryUI.elements.basic.UITextField;
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
        UITextField item = new UITextField("", "Placeholder...", ReadableColor.ORANGE, 1,
                UILabelAlignment.MIDDLE_CENTER);
        item.setPosition(new Point(10, 10));
        item.setSize(100, 20);
        item.setBackgroundColor(ReadableColor.GREY);
        this.screenContainer.addElement(item);

        // Always call super
        super.initGui();
    }
}
