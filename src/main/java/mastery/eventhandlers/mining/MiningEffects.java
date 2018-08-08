package mastery.eventhandlers.mining;

import mastery.capability.skillclasses.MiningMastery;
import mastery.util.MasteryUtils;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MiningEffects {

    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed breakingSpeed) {
        MiningMastery miningMastery = MasteryUtils.getMiningMastery(breakingSpeed.getEntityPlayer());
        float newSpeed = miningMastery.getMiningSpeed(breakingSpeed.getOriginalSpeed());
        breakingSpeed.setNewSpeed(newSpeed);
    }

    public void harvestBlocks(BlockEvent.HarvestDropsEvent harvestEvent) {
        if (harvestEvent.getDrops().size() > 0 && harvestEvent.getHarvester() != null) {
            int level = MasteryUtils.getMiningMastery(harvestEvent.getHarvester()).getLevel();
            harvestEvent.getDrops().get(0).setCount(harvestEvent.getDrops().get(0).getCount() + level);
        }
    }
}
