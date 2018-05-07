package mastery.eventhandlers;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MasteryClasses;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SaveLoadEventHandler {

	@SubscribeEvent
	public void joinWorld(EntityJoinWorldEvent joinWorldEvent) {
		if ((joinWorldEvent.getEntity() instanceof EntityPlayer)
				&& !joinWorldEvent.getEntity().getEntityWorld().isRemote) {
			IMastery mastery = joinWorldEvent.getEntity().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
			EntityPlayerMP player = (EntityPlayerMP) joinWorldEvent.getEntity();
			for (MASTERY_SPEC mastSpec : MASTERY_SPEC.values()) {
				MasteryClasses masteryClasses = mastery.getMasteries().get(mastSpec);
				MasteryMessage message = new MasteryMessage(masteryClasses.getSkillClass().order,
						masteryClasses.getLevel(), masteryClasses.getExperience(), false);
				PacketHandler.INSTANCE.sendTo(message, player);
			}
		}
	}
}
