package mastery.util;

import mastery.capability.world.BlockStats;
import mastery.capability.world.IBlockInfo;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockUtils {

    public static boolean isCrop(IBlockState state) {
        return state.getBlock() instanceof BlockCrops;
    }

    @SubscribeEvent
    void place(BlockEvent.PlaceEvent placeEvent) {
        if (placeEvent.getPlayer() != null && !placeEvent.getPlayer().getEntityWorld().isRemote) {
            IBlockInfo blockInfoMastery = MasteryUtils.getBlockInfoMastery(placeEvent.getWorld());
            blockInfoMastery.setPlacedByPlayerForPosition(placeEvent.getPos());
        }
    }

    @SubscribeEvent
    void mine(BlockEvent.HarvestDropsEvent event) {
        if (event.getHarvester() != null && !event.getHarvester().getEntityWorld().isRemote) {
            IBlockInfo blockInfoMastery = MasteryUtils.getBlockInfoMastery(event.getWorld());
            BlockStats statsForPosition = blockInfoMastery.getStatsForPosition(event.getPos());
            event.getHarvester().sendMessage(new TextComponentString(statsForPosition.toString()));
        }
    }
}
