package mastery.util;

import mastery.capability.world.IBlockInfo;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
        blockInfo.setBlockDestroyedForPosition(pos);
    }

    public static boolean isCrop(IBlockState state) {
        return state.getBlock() instanceof BlockCrops;
    }
}
