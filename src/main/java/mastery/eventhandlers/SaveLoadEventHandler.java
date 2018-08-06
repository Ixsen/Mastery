package mastery.eventhandlers;

import mastery.experience.IMastery;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MasteryClass;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
import mastery.util.MasteryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SaveLoadEventHandler {

    @SubscribeEvent
    public void joinWorld(EntityJoinWorldEvent joinWorldEvent) {
        if ((joinWorldEvent.getEntity() instanceof EntityPlayer)
                && !joinWorldEvent.getEntity().getEntityWorld().isRemote) {
            EntityPlayerMP player = (EntityPlayerMP) joinWorldEvent.getEntity();
            IMastery mastery = MasteryUtils.getMasteries(player);
            for (MASTERY_SPEC mastSpec : MASTERY_SPEC.values()) {
                MasteryClass masteryClass = mastery.getMasteries().get(mastSpec);
                MasteryMessage message = new MasteryMessage(masteryClass.getSkillClass().order, masteryClass.getLevel(),
                        masteryClass.getExperience(), false);
                PacketHandler.INSTANCE.sendTo(message, player);
            }
        }
    }
}
