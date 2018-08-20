package mastery.ui.views;

import org.lwjgl.util.ReadableColor;

import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.container.VanillaGuiScreen;
import mastery.ui.custom.elements.impl.UILabel;
import mastery.ui.custom.elements.impl.UILabel.UILabelAlignment;
import mastery.ui.custom.layouts.FreeformLayout;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends GuiScreen {

    public LevelUi() {
        super();
    }

    @Override
    public void initGui() {
        VanillaGuiScreen screen = VanillaGuiScreen.setup(this);
        screen.setLayout(new FreeformLayout());

        // COL 1
        screen.addElements(new FreeformLayout.Data(10, 10), this.getPanel(UILabelAlignment.TOP_LEFT));
        screen.addElements(new FreeformLayout.Data(10, 45), this.getPanel(UILabelAlignment.MIDDLE_LEFT));
        screen.addElements(new FreeformLayout.Data(10, 80), this.getPanel(UILabelAlignment.BOT_LEFT));

        // COL 1
        screen.addElements(new FreeformLayout.Data(120, 10), this.getPanel(UILabelAlignment.TOP_CENTER));
        screen.addElements(new FreeformLayout.Data(120, 45), this.getPanel(UILabelAlignment.MIDDLE_CENTER));
        screen.addElements(new FreeformLayout.Data(120, 80), this.getPanel(UILabelAlignment.BOT_CENTER));

        // COL 1
        screen.addElements(new FreeformLayout.Data(230, 10), this.getPanel(UILabelAlignment.TOP_RIGHT));
        screen.addElements(new FreeformLayout.Data(230, 45), this.getPanel(UILabelAlignment.MIDDLE_RIGHT));
        screen.addElements(new FreeformLayout.Data(230, 80), this.getPanel(UILabelAlignment.BOT_RIGHT));

    }

    private GuiPanel getPanel(UILabelAlignment alignment) {
        GuiPanel panel = new GuiPanel();
        panel.setSize(100, 30);
        panel.setBackgroundColor(ReadableColor.BLACK);
        UILabel label = new UILabel(alignment);
        label.setText(alignment.name());
        label.setSize(100, 30);
        panel.addElements(null, label);
        return panel;
    }
}
