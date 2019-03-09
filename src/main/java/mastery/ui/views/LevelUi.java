package mastery.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.oldui.elements.IUIElement;
import mastery.oldui.elements.UIMasteryView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends GuiScreen {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private HashMap<Integer, MasterySpec> buttonsID;

    private List<IUIElement> elements;

    public LevelUi() {
        super();
        this.setGuiSize(WIDTH, HEIGHT);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawGradientRect(108, 210, 4, 4, 0xFF222222, 0xFF222222);
        for (IUIElement el : this.elements) {
            el.drawElement(Minecraft.getMinecraft(), mouseX, mouseY);
        }
    }

    @Override
    public void initGui() {
        this.buttonsID = new HashMap<>();
        this.elements = new ArrayList<>();

        int y = 5;
        for (MasterySpec mastery : MasterySpec.values()) {
            // Start x position
            int x = 5;

            IUIElement masteryView = new UIMasteryView(mastery, x, y, 1, true, true);
            this.elements.add(masteryView);

            y += masteryView.getHeight() + 1;
        }
    }
}
