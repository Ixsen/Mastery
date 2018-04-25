package mastery.ui;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends GuiScreen {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 150;

    public LevelUi() {
        super();
        setGuiSize(WIDTH, HEIGHT);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        EntityPlayerSP player = mc.player;
        IMastery mastery = player.getCapability(MasteryProvider.MASTERY_CAPABILITY,null);
        GuiButton alchemy = new GuiButton(0,100,21,300,20,mastery.getMasteries().get(MASTERY_SPEC.ALCHEMY).toString());
        GuiButton athletics = new GuiButton(0,100,42,300,20,mastery.getMasteries().get(MASTERY_SPEC.ATHLETICS).toString());
        GuiButton combat = new GuiButton(0,100,63,300,20,mastery.getMasteries().get(MASTERY_SPEC.COMBAT).toString());
        GuiButton crafting = new GuiButton(0,100,84,300,20,mastery.getMasteries().get(MASTERY_SPEC.CRAFTING).toString());
        GuiButton farming = new GuiButton(0,100,105,300,20,mastery.getMasteries().get(MASTERY_SPEC.FARMING).toString());
        GuiButton husbandry = new GuiButton(0,100,126,300,20,mastery.getMasteries().get(MASTERY_SPEC.HUSBANDRY).toString());
        GuiButton mining = new GuiButton(0,100,147,300,20,mastery.getMasteries().get(MASTERY_SPEC.MINING).toString());
        GuiButton survival = new GuiButton(0,100,168,300,20,mastery.getMasteries().get(MASTERY_SPEC.SURVIVAL).toString());
        buttonList.add(alchemy);
        buttonList.add(athletics);
        buttonList.add(combat);
        buttonList.add(crafting);
        buttonList.add(farming);
        buttonList.add(husbandry);
        buttonList.add(mining);
        buttonList.add(survival);

        
        //GUILabel(FontRenderer, ID, X, Y, WIDTH, HEIGHT, TEXT_COLOR)
//        GuiLabel level = new GuiLabel(fontRenderer, 100, 2, 2, 200, 50, 4210752);
//        level.addLine("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        GuiLabel level2 = new GuiLabel(fontRenderer, 100, 2, 25, 200, 50, 4210752);
//        level.addLine("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
//        GuiLabel level3 = new GuiLabel(fontRenderer, 100, 2, 50, 200, 50, 4210752);
//        level.addLine("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
//        GuiLabel level4 = new GuiLabel(fontRenderer, 100, 2, 75, 200, 50, 1000);
//        level.addLine("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
//        labelList.add(level);
//        labelList.add(level2);
//        labelList.add(level3);
//        labelList.add(level4);
    }
}
