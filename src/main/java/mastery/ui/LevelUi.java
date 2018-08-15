package mastery.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mastery.MasteryMod;
import mastery.capability.skillclasses.MasteryClass;
import mastery.capability.skillclasses.MasterySpec;
import mastery.ui.elements.IUIElement;
import mastery.ui.elements.UIMasteryView;
import mastery.util.MasteryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends GuiScreen {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private HashMap<Integer, MasterySpec> buttonsID;

    private List<IUIElement> elements;

    private final ResourceLocation bar = new ResourceLocation(MasteryMod.modid, "textures/gui/expbarsheet.png");
    private final int BAR_WIDTH = 100;
    private final int BAR_HEIGHT = 6;

    public LevelUi() {
        super();
        this.setGuiSize(WIDTH, HEIGHT);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawGradientRect(108, 210, 4, 4, 0xFF222222, 0xFF222222);
        for (IUIElement el : elements) {
            el.drawElement(Minecraft.getMinecraft(), mouseX, mouseY);
        }
    }

    @Override
    public void initGui() {
        buttonsID = new HashMap<>();
        elements = new ArrayList<>();
        EntityPlayerSP player = this.mc.player;

        int y = 5;
        for (MasterySpec mastery : MasterySpec.values()) {
            // Start x position
            int x = 5;
            MasteryClass masteryClass = MasteryUtils.getMastery(player, mastery);

            IUIElement masteryView = new UIMasteryView(mastery, x, y, 1, true, true);
            this.elements.add(masteryView);

            y += masteryView.getHeight() + 1;
        }

    }

}
