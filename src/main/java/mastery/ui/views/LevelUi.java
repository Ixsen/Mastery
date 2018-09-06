package mastery.ui.views;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import masteryUI.elements.basic.UIButton;
import masteryUI.elements.basic.UILabel.UIAlignment;
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
        UIButton button = new UIButton("WOW", ReadableColor.CYAN, 1, UIAlignment.MIDDLE_CENTER);
        button.setPosition(new Point(10, 10));
        button.setSize(300, 40);
        this.screenContainer.addElement(button);

        // Always call super
        super.initGui();
    }
}
