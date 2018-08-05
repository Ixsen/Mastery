package mastery.util;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;

public class BlockUtil {

    private static boolean beetrootCheck(IBlockState state) {
        return state.getBlock() instanceof BlockBeetroot && state.getProperties().get(BlockBeetroot.BEETROOT_AGE).equals(3);
    }

    private static boolean cactusCheck(IBlockState state) {
        return state.getBlock() instanceof BlockCactus && ((Integer) state.getProperties().get(BlockCactus.AGE) > 7);
    }

    private static boolean sugarCaneCheck(IBlockState state) {
        return state.getBlock() instanceof BlockReed && ((Integer) state.getProperties().get(BlockReed.AGE) > 7);
    }

    private static boolean netherWartCheck(IBlockState state) {
        return state.getBlock() instanceof BlockNetherWart && state.getProperties().get(BlockNetherWart.AGE).equals(3);
    }

    private static boolean cocoaCheck(IBlockState state) {
        return state.getBlock() instanceof BlockCocoa && state.getProperties().get(BlockCocoa.AGE).equals(2);
    }

    private static boolean genericCheck(IBlockState state) {
        Block block = state.getBlock();
        return isCrop(state) && !(block instanceof BlockBeetroot) && state.getProperties().get(BlockCrops.AGE).equals(7);
    }

    public static boolean isCrop(IBlockState state) {
        return state.getBlock() instanceof BlockCrops;
    }

    /**
     * Return whether or not the current harvest state should give experience for farming
     *
     * @param state --
     * @return --
     */
    public static boolean shouldGetFarmingExp(IBlockState state) {
        return beetrootCheck(state) || cactusCheck(state) || sugarCaneCheck(state) ||
                netherWartCheck(state) || cocoaCheck(state) || genericCheck(state) ||
                state.getBlock() instanceof BlockMelon ||
                state.getBlock() instanceof BlockPumpkin;
    }
}
