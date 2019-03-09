package mastery.eventhandlers.farming;

import java.util.List;

import mastery.capability.player.skillclasses.FarmingMastery;
import mastery.util.MasteryUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FarmingEffects {

    @SubscribeEvent
    public void harvest(HarvestDropsEvent harvestDrop) {
        if (harvestDrop.getHarvester() != null && !harvestDrop.getWorld().isRemote) {
            if (FarmingUtils.shouldApplyFarming(harvestDrop.getState())) {
                List<ItemStack> drops = harvestDrop.getDrops();

                FarmingMastery farmingMastery = MasteryUtils.getFarmingMastery(harvestDrop.getHarvester());
                for (ItemStack itemStack : drops) {
                    if (farmingMastery.applyQuadDrop()) {
                        harvestDrop.getHarvester().sendMessage(new TextComponentString("Yeah 4 drop chance!"));
                        itemStack.setCount(itemStack.getCount() * 4);
                    } else if (farmingMastery.applyDoubleDrop()) {
                        harvestDrop.getHarvester().sendMessage(new TextComponentString("Yeah 2 drop chance!"));
                        itemStack.setCount(itemStack.getCount() * 2);
                    }
                }
            }

        }
    }

}
