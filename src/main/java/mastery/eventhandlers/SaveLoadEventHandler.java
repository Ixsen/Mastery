package mastery.eventhandlers;

import mastery.capability.player.IMastery;
import mastery.capability.player.skillclasses.MasteryClass;
import mastery.capability.player.skillclasses.MasterySpec;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
import mastery.common.util.MasteryUtils;
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
            for (MasterySpec mastSpec : MasterySpec.values()) {
                MasteryClass masteryClass = mastery.getMasteries().get(mastSpec);
                masteryClass.setPlayer(player);
                MasteryMessage message = new MasteryMessage(masteryClass.getSkillClass().order, masteryClass.getLevel(),
                        masteryClass.getExperience(), false);
                PacketHandler.INSTANCE.sendTo(message, player);
            }
        }
    }
}
