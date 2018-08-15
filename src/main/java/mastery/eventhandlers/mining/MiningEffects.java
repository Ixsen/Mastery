package mastery.eventhandlers.mining;

import mastery.capability.skillclasses.MiningMastery;
import mastery.eventhandlers.farming.FarmingUtils;
import mastery.util.MasteryUtils;
import net.minecraft.block.BlockOre;
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
                && !harvestEvent.getHarvester().getEntityWorld().isRemote) {
            if (harvestEvent.isSilkTouching()) {
                return;
            }

            // Apply only on non farming blocks
            if (!FarmingUtils.shouldApplyFarming(harvestEvent.getState()) && !harvestEvent.isSilkTouching()) {
                // Apply if the user is lucky and the resulting amount
                int extraDrops = MasteryUtils.getMiningMastery(harvestEvent.getHarvester())
                        .getExtraDropIfLucky(harvestEvent.getState().getBlock() instanceof BlockOre);
                if (extraDrops != 0) {
                    for (ItemStack item : harvestEvent.getDrops()) {
                        // TODO really silly but you can exploit player placed blocks like iron and gold
                        // Add the extra amount to every item stack ;)
                        item.setCount(item.getCount() + extraDrops);
                    }
                }
            }
        }
    }
}
