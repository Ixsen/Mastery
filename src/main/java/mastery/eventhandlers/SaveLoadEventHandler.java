package mastery.eventhandlers;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SaveLoadEventHandler {

    @SubscribeEvent
    public void joinWorld(PlayerEvent.LoadFromFile joinWorldEvent) {
//        if(joinWorldEvent.getEntityPlayer() != null && !joinWorldEvent.getEntityPlayer().getEntityWorld().isRemote) {
//            IMastery mastery = joinWorldEvent.getEntityPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
//            EntityPlayerMP player = (EntityPlayerMP) joinWorldEvent.getEntityPlayer();
//            player.sendMessage(new TextComponentString("YOU HAVE " + player.connection));
//            MasteryMessage message = new MasteryMessage(mastery.toIntArray());
//            PacketHandler.INSTANCE.sendTo(message, player);
//        }
    }
}
