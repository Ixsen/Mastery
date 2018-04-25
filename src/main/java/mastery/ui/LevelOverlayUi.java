package mastery.ui;

import org.lwjgl.opengl.GL11;

import mastery.MasteryMod;
import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
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

	private final ResourceLocation bar = new ResourceLocation(MasteryMod.modid, "textures/gui/expbarsheet.png");
	private final ResourceLocation mining = new ResourceLocation(MasteryMod.modid, "textures/gui/mining.png");
	private final int BACK_WIDTH = 102;
	private final int BACK_HEIGHT = 8;
	private final int BAR_WIDTH = 100;
	private final int BAR_HEIGHT = 6;
	private final int ICON_SIZE = 16;

	private boolean visible = false;
	private long lastTime = 0;
	private long currentTimeStamp = 0;
	private long maxShowTime = 5000; // 5 Sek

	private boolean draw_exp_numbers = true;

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

			mc.renderEngine.bindTexture(bar);

			// Draw Icon
			drawTexturedModalRect(0, 0, 0, 16, ICON_SIZE, ICON_SIZE);

			// Draw Labels
			drawString(mc.fontRenderer, "Mining Level: " + mastery.getMasteries().get(MASTERY_SPEC.MINING).getLevel(),
					ICON_SIZE + 2, 4, 0xFFFFFF);

			// Draw ExpBar
			mc.renderEngine.bindTexture(bar);
			drawTexturedModalRect(0, ICON_SIZE, 0, 0, BACK_WIDTH, BACK_HEIGHT);

			int currentValue = mastery.getMasteries().get(MASTERY_SPEC.MINING).getExperience() % 10;
			// TODO Füge Grenzen für max level ein

			double quotient = currentValue / 10d;
			int currentBarWidth = (int) (BAR_WIDTH * quotient);
			drawTexturedModalRect(1, ICON_SIZE + 1, 1, BACK_HEIGHT + 1, currentBarWidth, BAR_HEIGHT);

			if (draw_exp_numbers) {
				// Drak current exp number
				GL11.glPushMatrix();
				GL11.glScalef(0.5f, 0.5f, 0.5f);
				// Draw Labels
				drawString(mc.fontRenderer, currentValue + "/10", 4,
						2*ICON_SIZE + 4, 0xFFFFFF);
				GL11.glPopMatrix();
			}
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
