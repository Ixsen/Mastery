package mastery.ui;

import mastery.util.MasteryUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends GuiScreen {
    private static final int WIDTH = 150;
    private static final int HEIGHT = 150;

    public LevelUi() {
	super();
	this.setGuiSize(WIDTH, HEIGHT);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
	EntityPlayerSP player = this.mc.player;
	GuiButton alchemy = new GuiButton(0, 100, 21, 300, 20, MasteryUtils.getAlchemyMastery(player).toString());
	GuiButton athletics = new GuiButton(0, 100, 42, 300, 20, MasteryUtils.getAthleticsMastery(player).toString());
	GuiButton combat = new GuiButton(0, 100, 63, 300, 20, MasteryUtils.getCombatMastery(player).toString());
	GuiButton crafting = new GuiButton(0, 100, 84, 300, 20, MasteryUtils.getCraftingMastery(player).toString());
	GuiButton farming = new GuiButton(0, 100, 105, 300, 20, MasteryUtils.getFarmingMastery(player).toString());
	GuiButton husbandry = new GuiButton(0, 100, 126, 300, 20, MasteryUtils.getHusbandryMastery(player).toString());
	GuiButton mining = new GuiButton(0, 100, 147, 300, 20, MasteryUtils.getMiningMastery(player).toString());
	GuiButton survival = new GuiButton(0, 100, 168, 300, 20, MasteryUtils.getSurvivalMastery(player).toString());
	this.buttonList.add(alchemy);
	this.buttonList.add(athletics);
	this.buttonList.add(combat);
	this.buttonList.add(crafting);
	this.buttonList.add(farming);
	this.buttonList.add(husbandry);
	this.buttonList.add(mining);
	this.buttonList.add(survival);
    }
}
