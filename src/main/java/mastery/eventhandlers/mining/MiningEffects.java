package mastery.eventhandlers.mining;

import mastery.capability.player.skillclasses.MiningMastery;
import mastery.util.MasteryUtils;
import net.minecraft.item.ItemStack;
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

    @SubscribeEvent
    public void harvestBlocks(BlockEvent.HarvestDropsEvent harvestEvent) {
        if (harvestEvent.getDrops().size() > 0 && harvestEvent.getHarvester() != null
                && !harvestEvent.getHarvester().getEntityWorld().isRemote && !harvestEvent.isSilkTouching()
                && MiningUtils.isOre(harvestEvent.getState().getBlock(), true)) {

            // Apply if the user is lucky and the resulting amount
            int extraDrops = MasteryUtils.getMiningMastery(harvestEvent.getHarvester()).getExtraDropIfLucky();
            if (extraDrops != 0) {
                for (ItemStack item : harvestEvent.getDrops()) {
                    // Add the extra amount to every item stack ;)
                    item.setCount(item.getCount() + extraDrops);
                }
            }
        }
    }
}
