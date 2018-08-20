package mastery.oldui.elements;

import org.lwjgl.opengl.GL11;

import mastery.MasteryMod;
import mastery.capability.skillclasses.MasteryClass;
import mastery.capability.skillclasses.MasterySpec;
import mastery.resource.MasteryImageLoader;
import mastery.util.MasteryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UIMasteryView extends Gui implements IUIElement {
    // Resources - Area
    private final ResourceLocation bar = new ResourceLocation(MasteryMod.modid, "textures/gui/expbarsheet.png");

    // ExpBar - Area
    private final int BACK_WIDTH = 102;
    private final int BACK_HEIGHT = 8;
    private final int BAR_WIDTH = 100;
    private final int BAR_HEIGHT = 6;
    private final int LEVEL_WIDTH = 13;
    private final int LEVEL_HEIGH = 8;

    // ICON - Area
    private final int ICON_SIZE = 64;
    private float ICON_SCALE = 0.125f;

    private int globalXOffset;
    private int globalYOffset;
    private double alpha = 1;
    private MasterySpec spec;
    private boolean showUITitle = true;
    private boolean showCurrentExp = true;

    public UIMasteryView(MasterySpec spec, int globalXOffset, int globalYOffset, double alpha, boolean showUITitle,
            boolean showCurrentExp) {
        super();
        this.spec = spec;
        this.globalXOffset = globalXOffset;
        this.globalYOffset = globalYOffset;
        this.alpha = alpha;
        this.showUITitle = showUITitle;
        this.showCurrentExp = showCurrentExp;
    }

    @Override
    public void drawElement(Minecraft mc, int mouseX, int mouseY) {
        // Get player and mastery class
        EntityPlayerSP player = mc.player;
        MasteryClass masteryClass = MasteryUtils.getMastery(player, this.spec);

        // Draw the icon for the mastery
        mc.renderEngine.bindTexture(MasteryImageLoader.getImage(masteryClass.getSkillClass()));
        GL11.glPushMatrix();
        GL11.glScalef(this.ICON_SCALE, this.ICON_SCALE, this.ICON_SCALE);
        GL11.glColor4d(1, 1, 1, this.alpha);
        int xOffset = (int) (this.globalXOffset * (1 / this.ICON_SCALE));
        int yOffset = (int) (this.globalYOffset * (1 / this.ICON_SCALE));
        drawModalRectWithCustomSizedTexture(xOffset, yOffset, 0, 0, this.ICON_SIZE, this.ICON_SIZE, this.ICON_SIZE,
                this.ICON_SIZE);
        GL11.glPopMatrix();

        // Draw the exp bar
        mc.renderEngine.bindTexture(this.bar);
        GL11.glPushMatrix();
        GL11.glScalef(1, 1, 1);
        GL11.glColor4d(1, 1, 1, this.alpha);
        this.drawTexturedModalRect(this.globalXOffset, this.globalYOffset + (this.ICON_SCALE * this.ICON_SIZE), 0, 0,
                this.BACK_WIDTH, this.BACK_HEIGHT);
        double quotient = masteryClass.getExperience() / (float) masteryClass.getNextLevelExp();
        int currentBarWidth = (int) (this.BAR_WIDTH * quotient);
        this.drawTexturedModalRect(this.globalXOffset + 1, this.globalYOffset + (this.ICON_SCALE * this.ICON_SIZE) + 1,
                1, this.BACK_HEIGHT + 1, currentBarWidth, this.BAR_HEIGHT);
        GL11.glPopMatrix();

        // Draw the level box
        GL11.glPushMatrix();
        GL11.glScalef(1, 1, 1);
        GL11.glColor4d(1, 1, 1, this.alpha);
        this.drawTexturedModalRect(this.globalXOffset + (this.ICON_SCALE * this.ICON_SIZE), this.globalYOffset, 0,
                2 * this.BACK_HEIGHT, this.LEVEL_WIDTH, this.LEVEL_HEIGH);
        GL11.glPopMatrix();

        // Draw current level
        String level = "" + masteryClass.getLevel();
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4d(1, 1, 1, this.alpha);
        int levelX = (int) (this.globalXOffset + (this.ICON_SCALE * this.ICON_SIZE) + 2) * 2;
        int levelY = (this.globalYOffset + 2) * 2 + 1;
        this.drawString(mc.fontRenderer, level, levelX, levelY, 0xFFFFFFFF);
        GL11.glPopMatrix();

        if (this.showUITitle) {
            // Draw current level
            String title = "" + masteryClass.getName();
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            GL11.glColor4d(1, 1, 1, this.alpha);
            int titleX = (int) ((this.globalXOffset + (this.ICON_SCALE * this.ICON_SIZE) + 2) + this.LEVEL_WIDTH) * 2;
            int titleY = (this.globalYOffset + 2) * 2 + 1;
            this.drawString(mc.fontRenderer, title, titleX, titleY, 0xFFFFFFFF);
            GL11.glPopMatrix();
        }
        if (this.showCurrentExp) {
            // Draw current exp
            String exp = "" + masteryClass.getExperience() + " / " + masteryClass.getNextLevelExp();
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            GL11.glColor4d(1, 1, 1, this.alpha);
            int expX = (this.globalXOffset + 2) * 2;
            int expY = (int) ((this.globalYOffset + (this.ICON_SCALE * this.ICON_SIZE) + 2) * 2);
            this.drawString(mc.fontRenderer, exp, expX, expY, 0xFFFFFFFF);
            GL11.glPopMatrix();
        }
    }

    @Override
    public int getHeight() {
        return (int) ((this.ICON_SCALE * this.ICON_SIZE + this.LEVEL_HEIGH));
    }

    @Override
    public int getWidth() {
        return (int) (this.ICON_SCALE * this.LEVEL_WIDTH);
    }
}
