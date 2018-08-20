package mastery.ui.views;

import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.container.VanillaGuiScreen;
import de.johni0702.minecraft.gui.layout.VerticalLayout;
import de.johni0702.minecraft.gui.utils.Colors;
import mastery.capability.skillclasses.MasterySpec;
import mastery.ui.custom.elements.impl.UIMasteryPreview;
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

        GuiPanel panel2 = new GuiPanel();
        panel2.setBackgroundColor(Colors.DKGREY);
        panel2.setLayout(new FreeformLayout());

        GuiPanel panel = new GuiPanel();
        panel.setLayout(new VerticalLayout().setSpacing(2));

        for (MasterySpec mastery : MasterySpec.values()) {
            UIMasteryPreview view = this.getPreview(mastery);
            panel.addElements(null, view);
        }
        panel2.addElements(new FreeformLayout.Data(2, 2), panel);
        panel2.setSize(panel2.calcMinSize().getWidth() + 2, panel2.calcMinSize().getHeight() + 2);

        screen.addElements(new FreeformLayout.Data(10, 10), panel2);
    }

    private UIMasteryPreview getPreview(MasterySpec mastery) {
        UIMasteryPreview preview = new UIMasteryPreview(mastery);
        return preview;
    }
}
