package mastery.capability.world;

import java.util.HashMap;

import net.minecraft.util.math.BlockPos;

public class BlockInfo implements IBlockInfo {

    private HashMap<BlockPos, BlockStats> blockInfo = new HashMap<>();

    @Override
    public HashMap<BlockPos, BlockStats> getFullInformation() {
        return this.blockInfo;
    }

    @Override
    public void setFullInformation(HashMap<BlockPos, BlockStats> map) {
        this.blockInfo = map;
    }

    @Override
    public BlockStats getStatsForPosition(BlockPos pos) {
        BlockStats stats = this.blockInfo.get(pos);
        if (stats == null) {
            return BlockStats.FACTORY.createNoPreviousInteraction();
        }
        return stats;
    }

    @Override
    public void setStatsForPosition(BlockPos pos, BlockStats stats) {
        this.blockInfo.put(pos, stats);
    }

    @Override
    public void setPlacedByPlayerForPosition(BlockPos pos) {
        this.blockInfo.put(pos, BlockStats.FACTORY.createPlayerPlaced());
    }

    @Override
    public void setContainerOpenedForPosition(BlockPos pos) {
        this.blockInfo.put(pos, BlockStats.FACTORY.createPlayerOpened());
    }

    @Override
    public void setBlockDestroyedForPosition(BlockPos pos) {
        this.blockInfo.remove(pos);
    }
}
