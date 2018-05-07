package mastery.ui;

import org.lwjgl.opengl.GL11;

import mastery.MasteryMod;
import mastery.configuration.MasteryConfiguration;
import mastery.eventsystem.IMasteryEventListener;
import mastery.eventsystem.MasteryEvent;
import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MasteryClasses;
import mastery.resource.MasteryImageLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LevelOverlayUi extends Gui implements IMasteryEventListener {
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
	private MASTERY_SPEC currentMastery = MASTERY_SPEC.MINING;

	public LevelOverlayUi() {
		super();
		MasteryMod.getEventHandler().addListener(this);
	}

	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		// Return because overlay is deactivated
		if (!MasteryConfiguration.UI_OVERLAY.isActive) {
			return;
		}
		if (visible) {
			long stamp = System.currentTimeMillis();
			lastTime -= stamp - currentTimeStamp;
			currentTimeStamp = stamp;
			if (lastTime <= 0) {
				lastTime = 0;
				visible = false;
			}
		}
		// The element layer describes the order where the overlay is drawn
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && visible) {
			Minecraft mc = Minecraft.getMinecraft();

			// Get Player and masteries
			EntityPlayerSP player = mc.player;
			IMastery mastery = player.getCapability(MasteryProvider.MASTERY_CAPABILITY, null);

			// Draw Overlay
			drawOverlay(mc, mastery, currentMastery);
		}
	}

	private void drawOverlay(Minecraft mc, IMastery mastery, MASTERY_SPEC activeMastery) {
		// Get mastery
		MasteryClasses masteryInstance = mastery.getMasteries().get(activeMastery);

		// Draw the icon for the mastery
		mc.renderEngine.bindTexture(MasteryImageLoader.getImage(activeMastery));
		GL11.glPushMatrix();
		GL11.glScalef(ICON_SCALE, ICON_SCALE, ICON_SCALE);
		GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
		int xOffset = (int) (MasteryConfiguration.UI_OVERLAY.uiOffsetX * (1 / ICON_SCALE));
		int yOffset = (int) (MasteryConfiguration.UI_OVERLAY.uiOffsetY * (1 / ICON_SCALE));
		drawModalRectWithCustomSizedTexture(xOffset, yOffset, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
		GL11.glPopMatrix();

		// Draw the exp bar
		mc.renderEngine.bindTexture(bar);
		GL11.glPushMatrix();
		GL11.glScalef(1, 1, 1);
		GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
		drawTexturedModalRect(MasteryConfiguration.UI_OVERLAY.uiOffsetX,
				MasteryConfiguration.UI_OVERLAY.uiOffsetY + (ICON_SCALE * ICON_SIZE), 0, 0, BACK_WIDTH, BACK_HEIGHT);
		double quotient = masteryInstance.getExperience() / (float) masteryInstance.getNextLevelExp();
		int currentBarWidth = (int) (BAR_WIDTH * quotient);
		drawTexturedModalRect(MasteryConfiguration.UI_OVERLAY.uiOffsetX + 1,
				MasteryConfiguration.UI_OVERLAY.uiOffsetY + (ICON_SCALE * ICON_SIZE) + 1, 1, BACK_HEIGHT + 1,
				currentBarWidth, BAR_HEIGHT);
		GL11.glPopMatrix();

		// Draw the level box
		GL11.glPushMatrix();
		GL11.glScalef(1, 1, 1);
		GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
		drawTexturedModalRect(MasteryConfiguration.UI_OVERLAY.uiOffsetX + (ICON_SCALE * ICON_SIZE),
				MasteryConfiguration.UI_OVERLAY.uiOffsetY, 0, 2 * BACK_HEIGHT, LEVEL_WIDTH, LEVEL_HEIGH);
		GL11.glPopMatrix();

		// Draw current level
		String level = "" + masteryInstance.getLevel();
		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
		int levelX = (int) (MasteryConfiguration.UI_OVERLAY.uiOffsetX + (ICON_SCALE * ICON_SIZE) + 2) * 2;
		int levelY = (MasteryConfiguration.UI_OVERLAY.uiOffsetY + 2) * 2 + 1;
		drawString(mc.fontRenderer, level, levelX, levelY, 0xFFFFFFFF);
		GL11.glPopMatrix();

		if (MasteryConfiguration.UI_OVERLAY.uiShowTitle) {
			// Draw current level
			String title = "" + masteryInstance.getName();
			GL11.glPushMatrix();
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
			int titleX = (int) ((MasteryConfiguration.UI_OVERLAY.uiOffsetX + (ICON_SCALE * ICON_SIZE) + 2)
					+ LEVEL_WIDTH) * 2;
			int titleY = (MasteryConfiguration.UI_OVERLAY.uiOffsetY + 2) * 2 + 1;
			drawString(mc.fontRenderer, title, titleX, titleY, 0xFFFFFFFF);
			GL11.glPopMatrix();
		}
		if (MasteryConfiguration.UI_OVERLAY.uiShowCurrentExp) {
			// Draw current exp
			String exp = "" + masteryInstance.getExperience() + " / " + masteryInstance.getNextLevelExp();
			GL11.glPushMatrix();
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			GL11.glColor4d(1, 1, 1, MasteryConfiguration.UI_OVERLAY.uiAlphaValue);
			int expX = (MasteryConfiguration.UI_OVERLAY.uiOffsetX + 2) * 2;
			int expY = (int) ((MasteryConfiguration.UI_OVERLAY.uiOffsetY + (ICON_SCALE * ICON_SIZE) + 2) * 2);
			drawString(mc.fontRenderer, exp, expX, expY, 0xFFFFFFFF);
			GL11.glPopMatrix();
		}
	}

	private void show(MASTERY_SPEC spec) {
		if (MasteryConfiguration.UI_OVERLAY.isActive) {
			visible = true;
			currentTimeStamp = System.currentTimeMillis();
			currentMastery = spec;
			lastTime = MasteryConfiguration.UI_OVERLAY.overlayTime;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mastery.eventsystem.IMasteryEventListener#performEvent(mastery.eventsystem.
	 * MasteryEvent)
	 */
	@Override
	public void performEvent(MasteryEvent event) {
		switch (event.getType()) {
		case PLAYER_EXP_CHANGED:
			if (event.getSource() instanceof MASTERY_SPEC) {
				if (event.getTarget() instanceof Boolean) {
					boolean showUI = (Boolean) event.getTarget();
					if (showUI) {
						show((MASTERY_SPEC) event.getSource());
					}
				}
			}
			break;
		default:
			break;
		}

	}

}
