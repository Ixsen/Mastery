package mastery.oldui;

import org.lwjgl.opengl.GL11;

import mastery.MasteryMod;
import mastery.capability.player.IMastery;
import mastery.capability.player.skillclasses.MasteryClass;
import mastery.capability.player.skillclasses.MasterySpec;
import mastery.configuration.MasteryConfiguration;
import mastery.eventsystem.MasteryEvent;
import mastery.resource.MasteryImageLoader;
import mastery.util.MasteryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LevelOverlayUi extends Gui {
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

    // UI State - Area
    private boolean visible = false;
    private long lastTime = 0;
    private long currentTimeStamp = 0;

    // UI Input - Area
    private MasterySpec currentMastery = MasterySpec.MINING;

    public LevelOverlayUi() {
        super();
        MasteryMod.getEventHandler().addListener(this::performEvent);
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        // Return because overlay is deactivated
        if (!MasteryConfiguration.UI_OVERLAY.isActive) {
            return;
        }
        if (this.visible) {
            long stamp = System.currentTimeMillis();
            this.lastTime -= stamp - this.currentTimeStamp;
            this.currentTimeStamp = stamp;
            if (this.lastTime <= 0) {
                this.lastTime = 0;
                this.visible = false;
            }
        }
        // The element layer describes the order where the overlay is drawn
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && this.visible) {
            Minecraft mc = Minecraft.getMinecraft();

            // Get Player and masteries
            EntityPlayerSP player = mc.player;
            IMastery mastery = MasteryUtils.getMasteries(player);
            // Draw Overlay
            this.drawOverlay(mc, mastery, this.currentMastery);
        }
    }

    private void drawOverlay(Minecraft mc, IMastery mastery, MasterySpec activeMastery) {
        // Get mastery
        MasteryClass masteryInstance = mastery.getMasteries().get(activeMastery);

        // Draw the icon for the mastery
        mc.renderEngine.bindTexture(MasteryImageLoader.getImage(activeMastery));
        GL11.glPushMatrix();
        GL11.glScalef(this.ICON_SCALE, this.ICON_SCALE, this.ICON_SCALE);
        GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
        int xOffset = (int) (MasteryConfiguration.UI_OVERLAY.uiOffsetX * (1 / this.ICON_SCALE));
        int yOffset = (int) (MasteryConfiguration.UI_OVERLAY.uiOffsetY * (1 / this.ICON_SCALE));
        drawModalRectWithCustomSizedTexture(xOffset, yOffset, 0, 0, this.ICON_SIZE, this.ICON_SIZE, this.ICON_SIZE,
                this.ICON_SIZE);
        GL11.glPopMatrix();

        // Draw the exp bar
        mc.renderEngine.bindTexture(this.bar);
        GL11.glPushMatrix();
        GL11.glScalef(1, 1, 1);
        GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
        this.drawTexturedModalRect(MasteryConfiguration.UI_OVERLAY.uiOffsetX,
                MasteryConfiguration.UI_OVERLAY.uiOffsetY + (this.ICON_SCALE * this.ICON_SIZE), 0, 0, this.BACK_WIDTH,
                this.BACK_HEIGHT);
        double quotient = masteryInstance.getExperience() / (float) masteryInstance.getNextLevelExp();
        int currentBarWidth = (int) (this.BAR_WIDTH * quotient);
        this.drawTexturedModalRect(MasteryConfiguration.UI_OVERLAY.uiOffsetX + 1,
                MasteryConfiguration.UI_OVERLAY.uiOffsetY + (this.ICON_SCALE * this.ICON_SIZE) + 1, 1,
                this.BACK_HEIGHT + 1, currentBarWidth, this.BAR_HEIGHT);
        GL11.glPopMatrix();

        // Draw the level box
        GL11.glPushMatrix();
        GL11.glScalef(1, 1, 1);
        GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
        this.drawTexturedModalRect(MasteryConfiguration.UI_OVERLAY.uiOffsetX + (this.ICON_SCALE * this.ICON_SIZE),
                MasteryConfiguration.UI_OVERLAY.uiOffsetY, 0, 2 * this.BACK_HEIGHT, this.LEVEL_WIDTH, this.LEVEL_HEIGH);
        GL11.glPopMatrix();

        // Draw current level
        String level = "" + masteryInstance.getLevel();
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
        int levelX = (int) (MasteryConfiguration.UI_OVERLAY.uiOffsetX + (this.ICON_SCALE * this.ICON_SIZE) + 2) * 2;
        int levelY = (MasteryConfiguration.UI_OVERLAY.uiOffsetY + 2) * 2 + 1;
        this.drawString(mc.fontRenderer, level, levelX, levelY, 0xFFFFFFFF);
        GL11.glPopMatrix();

        if (MasteryConfiguration.UI_OVERLAY.uiShowTitle) {
            // Draw current level
            String title = "" + masteryInstance.getName();
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
            int titleX = (int) ((MasteryConfiguration.UI_OVERLAY.uiOffsetX + (this.ICON_SCALE * this.ICON_SIZE) + 2)
                    + this.LEVEL_WIDTH) * 2;
            int titleY = (MasteryConfiguration.UI_OVERLAY.uiOffsetY + 2) * 2 + 1;
            this.drawString(mc.fontRenderer, title, titleX, titleY, 0xFFFFFFFF);
            GL11.glPopMatrix();
        }
        if (MasteryConfiguration.UI_OVERLAY.uiShowCurrentExp) {
            // Draw current exp
            String exp = "" + masteryInstance.getExperience() + " / " + masteryInstance.getNextLevelExp();
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
            int expX = (MasteryConfiguration.UI_OVERLAY.uiOffsetX + 2) * 2;
            int expY = (int) ((MasteryConfiguration.UI_OVERLAY.uiOffsetY + (this.ICON_SCALE * this.ICON_SIZE) + 2) * 2);
            this.drawString(mc.fontRenderer, exp, expX, expY, 0xFFFFFFFF);
            GL11.glPopMatrix();
        }
    }

    private void show(MasterySpec spec) {
        if (MasteryConfiguration.UI_OVERLAY.isActive) {
            this.visible = true;
            this.currentTimeStamp = System.currentTimeMillis();
            this.currentMastery = spec;
            this.lastTime = MasteryConfiguration.UI_OVERLAY.overlayTime;
        }
    }

    private void performEvent(MasteryEvent event) {
        switch (event.getType()) {
        case PLAYER_EXP_CHANGED:
            if (event.getSource() instanceof MasterySpec) {
                if (event.getTarget() instanceof Boolean) {
                    boolean showUI = (Boolean) event.getTarget();
                    if (showUI) {
                        this.show((MasterySpec) event.getSource());
                    }
                }
            }
            break;
        default:
            break;
        }

    }

}
