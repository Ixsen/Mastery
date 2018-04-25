package mastery.ui;

import org.lwjgl.opengl.GL11;

import mastery.MasteryMod;
import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MasteryClasses;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
import mastery.resource.MasteryImageLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LevelOverlayUi extends Gui {

	public static int COLOR_BLUE = 0xFF0000FF; //BLUE
	public static int COLOR_GREEN = 0xFF00FF00; //GREEN
	public static int COLOR_RED = 0xFFFF0000;
	public static int COLOR_WHITE = 0xFFFFFFFF;
	public static int COLOR_WHITE_TRANS = 67108863;
	
	//Resources - Area
	private final ResourceLocation bar = new ResourceLocation(MasteryMod.modid, "textures/gui/expbarsheet.png");
	
	//ExpBar - Area
	private final int BACK_WIDTH = 102;
	private final int BACK_HEIGHT = 8;
	private final int BAR_WIDTH = 100;
	private final int BAR_HEIGHT = 6;
	private final int LEVEL_WIDTH = 13;
	private final int LEVEL_HEIGH = 8;
	
	//ICON - Area
	private final int ICON_SIZE = 64;
	private float ICON_SCALE = 0.125f;

	//UI State - Area
	private boolean visible = false;
	private long lastTime = 0;
	private long currentTimeStamp = 0;
	private long maxShowTime = 50000; // 5 Sek

	//UI - Options
	private static int uiOffsetX = 3;
	private static int uiOffsetY = 3;
	private static double uiAlphaValue = 0.6;
	private static boolean uiShowTitle = true;
	private static boolean uiShowCurrentExp = true;
	
	public static MASTERY_SPEC currentMastery = MASTERY_SPEC.MINING;

	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
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

			//Draw Overlay
			drawLargeOverlay(mc, mastery, currentMastery);
		}
	}

	private void drawLargeOverlay(Minecraft mc, IMastery mastery, MASTERY_SPEC activeMastery) {
		uiOffsetX = 3;
		uiOffsetY = 3;
		uiShowTitle = false;
		
		//Get mastery
		MasteryClasses masteryInstance = mastery.getMasteries().get(activeMastery);
		
		// Draw the icon for the mastery
		mc.renderEngine.bindTexture(MasteryImageLoader.getImage(activeMastery));
		GL11.glPushMatrix();
		GL11.glScalef(ICON_SCALE, ICON_SCALE, ICON_SCALE);
		GL11.glColor4d(1, 1, 1, uiAlphaValue);
		int xOffset = (int) (uiOffsetX * (1/ICON_SCALE));
		int yOffset = (int) (uiOffsetY * (1/ICON_SCALE));
		drawModalRectWithCustomSizedTexture(xOffset, yOffset, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
		GL11.glPopMatrix();
		
		//Draw the exp bar
		mc.renderEngine.bindTexture(bar);
		GL11.glPushMatrix();
		GL11.glScalef(1,1,1);
		GL11.glColor4d(1, 1, 1, uiAlphaValue);
		drawTexturedModalRect(uiOffsetX, uiOffsetY + (ICON_SCALE*ICON_SIZE), 0, 0, BACK_WIDTH, BACK_HEIGHT);
		double quotient = (double) ((masteryInstance.getExperience() % 10) / 10d);
		int currentBarWidth = (int) (BAR_WIDTH * quotient);
		drawTexturedModalRect(uiOffsetX + 1, uiOffsetY + (ICON_SCALE*ICON_SIZE) + 1, 1, BACK_HEIGHT + 1, currentBarWidth, BAR_HEIGHT);
		GL11.glPopMatrix();
		
		//Draw the level box
		GL11.glPushMatrix();
		GL11.glScalef(1,1,1);
		GL11.glColor4d(1, 1, 1, uiAlphaValue);
		drawTexturedModalRect(uiOffsetX+(ICON_SCALE*ICON_SIZE), uiOffsetY, 0, 2*BACK_HEIGHT, LEVEL_WIDTH, LEVEL_HEIGH);
		GL11.glPopMatrix();

		//Draw current level
		String level = "" + masteryInstance.getLevel();
		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		GL11.glColor4d(1, 1, 1, uiAlphaValue);
		int levelX = (int) (uiOffsetX+(ICON_SCALE*ICON_SIZE)+2) * 2;
		int levelY = (uiOffsetY+2) * 2 + 1;
		drawString(mc.fontRenderer, level, levelX , levelY, 0xFFFFFFFF);
		GL11.glPopMatrix();

		if (uiShowTitle) {
			//Draw current level
			String title = "" + masteryInstance.getName();
			GL11.glPushMatrix();
			GL11.glScalef(0.5f,0.5f,0.5f);
			GL11.glColor4d(1, 1, 1, uiAlphaValue);
			int titleX = (int) ((uiOffsetX+(ICON_SCALE*ICON_SIZE)+2) + LEVEL_WIDTH) * 2;
			int titleY = (uiOffsetY+2) * 2 + 1;
			drawString(mc.fontRenderer, title, titleX, titleY, 0xFFFFFFFF);
			GL11.glPopMatrix();
		}
		if (uiShowCurrentExp) {
			//Draw current exp
			String exp = "" + masteryInstance.getExperience() + " / " + masteryInstance.getNextLevelExp();
			GL11.glPushMatrix();
			GL11.glScalef(0.5f,0.5f,0.5f);
			GL11.glColor4d(1, 1, 1, uiAlphaValue);
			int expX = (uiOffsetX + 2) * 2;
			int expY = (int) ((uiOffsetY + (ICON_SCALE*ICON_SIZE) + 2) * 2);
			drawString(mc.fontRenderer, exp, expX, expY, 0xFFFFFFFF);
			GL11.glPopMatrix();
		}
	}
	
	@SubscribeEvent
	public void breakBlock(BlockEvent.BreakEvent breakEvent) { // TODO MINING and FARMING
		if (!breakEvent.getPlayer().getEntityWorld().isRemote) {
			visible = true;
			currentTimeStamp = System.currentTimeMillis();
			lastTime = maxShowTime;
		}
	}

}
