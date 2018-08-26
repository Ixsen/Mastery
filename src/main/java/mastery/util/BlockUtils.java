package mastery.util;

import mastery.capability.world.BlockStats;
import mastery.capability.world.IBlockInfo;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockUtils {

    public static void playerPlacedBlock(BlockPos pos, World world) {
        IBlockInfo blockInfoMastery = MasteryUtils.getBlockInfoMastery(world);
        blockInfoMastery.setPlacedByPlayerForPosition(pos);

    }

    public static void playerOpenedContainer(BlockPos pos, World world) {
        IBlockInfo blockInfo = MasteryUtils.getBlockInfoMastery(world);
        blockInfo.setContainerOpenedForPosition(pos);
    }

    public static void blockWasBroken(BlockPos pos, World world) {
        IBlockInfo blockInfo = MasteryUtils.getBlockInfoMastery(world);
        blockInfo.setBlockDestroyedAtPos(pos);
    }

    public static boolean isCrop(IBlockState state) {
        return state.getBlock() instanceof BlockCrops;
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
