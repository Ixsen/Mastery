package mastery.util;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;

public class BlockUtils {

    public static boolean isCrop(IBlockState state) {
        return state.getBlock() instanceof BlockCrops;
    }
}
