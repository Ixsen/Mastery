package mastery.capability.world;

import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

public class BlockInfoPersistenceManager implements Capability.IStorage<IBlockInfo> {

    private static final String TAG_X = "x";
    private static final String TAG_Y = "y";
    private static final String TAG_Z = "z";
    private static final String TAG_OPENED = "wasOpened";
    private static final String TAG_PLAYER_PLACED = "placedByPlayer";

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IBlockInfo> capability, IBlockInfo iBlockInfo, EnumFacing side) {
        return this.getNBTMasteryMap(iBlockInfo);
    }

    private NBTTagList getNBTMasteryMap(IBlockInfo mastery) {
        NBTTagList blockInfoList = new NBTTagList();
        for (Map.Entry<BlockPos, BlockStats> blockPosBlockStatsEntry : mastery.getFullInformation().entrySet()) {
            blockInfoList.appendTag(this.getNBTMap(blockPosBlockStatsEntry));
        }
        return blockInfoList;
    }

    private NBTTagCompound getNBTMap(Map.Entry<BlockPos, BlockStats> mapEntry) {
        NBTTagCompound blockInfoMap = new NBTTagCompound();
        BlockPos pos = mapEntry.getKey();
        BlockStats stats = mapEntry.getValue();

        blockInfoMap.setInteger(TAG_X, pos.getX());
        blockInfoMap.setInteger(TAG_Y, pos.getY());
        blockInfoMap.setInteger(TAG_Z, pos.getZ());
        blockInfoMap.setBoolean(TAG_OPENED, stats.wasOpened());
        blockInfoMap.setBoolean(TAG_PLAYER_PLACED, stats.isPlacedByPlayer());

        return blockInfoMap;
    }

    @Override
    public void readNBT(Capability<IBlockInfo> capability, IBlockInfo instance, EnumFacing side, NBTBase nbt) {
        for (NBTBase nbtBase : (NBTTagList) nbt) {
            NBTTagCompound savedMap = (NBTTagCompound) nbtBase;
            BlockPos blockPos = new BlockPos(savedMap.getInteger(TAG_X), savedMap.getInteger(TAG_Y),
                    savedMap.getInteger(TAG_Z));
            BlockStats stats = new BlockStats(savedMap.getBoolean(TAG_OPENED), savedMap.getBoolean(TAG_PLAYER_PLACED));
            instance.setStatsForPosition(blockPos, stats);
        }
    }
}
