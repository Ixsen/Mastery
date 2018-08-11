package mastery.eventhandlers.mining;

import mastery.capability.skillclasses.MiningMastery;
import mastery.eventhandlers.AbstractExperienceHandler;
import mastery.util.MasteryUtils;
import mastery.util.NetworkUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MiningExperience extends AbstractExperienceHandler {

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        if (!breakEvent.getPlayer().getEntityWorld().isRemote) {
            EntityPlayerMP player = (EntityPlayerMP) breakEvent.getPlayer();
            MiningMastery miningMastery = MasteryUtils.getMiningMastery(player);
            miningMastery.increaseExperience();
            NetworkUtils.sendExpToPlayer(miningMastery, player);
        }
    }

    /*
     * TNT MINING
     */
}
