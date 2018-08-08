package mastery.eventhandlers.mining;

import mastery.capability.skillclasses.FarmingMastery;
import mastery.capability.skillclasses.MiningMastery;
import mastery.eventhandlers.farming.FarmingUtils;
import mastery.util.MasteryUtils;
import mastery.util.NetworkUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MiningExperience {

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        if (!breakEvent.getPlayer().getEntityWorld().isRemote) {
            EntityPlayerMP player = (EntityPlayerMP) breakEvent.getPlayer();
            if (FarmingUtils.shouldGetFarmingExp(breakEvent.getState())) {
                FarmingMastery farmingMastery = MasteryUtils.getFarmingMastery(player);
                farmingMastery.increaseExperience();
                NetworkUtils.sendExpToPlayer(farmingMastery, player);
            } else {
                MiningMastery miningMastery = MasteryUtils.getMiningMastery(player);
                miningMastery.increaseExperience();
                NetworkUtils.sendExpToPlayer(miningMastery, player);
            }
        }
    }

}
