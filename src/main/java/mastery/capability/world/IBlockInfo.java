package mastery.capability.world;

import java.util.HashMap;

import net.minecraft.util.math.BlockPos;

public interface IBlockInfo {

    HashMap<BlockPos, BlockStats> getFullInformation();

    void setFullInformation(HashMap<BlockPos, BlockStats> map);

    BlockStats getStatsForPosition(BlockPos pos);

    void setStatsForPosition(BlockPos pos, BlockStats stats);

    /**
     * Also sets openened automatically
     * 
     * @param pos
     *            --
     */
    void setPlacedByPlayerForPosition(BlockPos pos);

    void setContainerOpenedForPosition(BlockPos pos);

    void setBlockDestroyedAtPos(BlockPos pos);
}
