package mastery.eventhandlers.farming;

import mastery.common.util.BlockUtils;
import net.minecraft.block.BlockBeetroot;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockMelon;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockReed;
import net.minecraft.block.state.IBlockState;

public class FarmingUtils {

    /**
     * Return whether or not the current harvest state should give capability for farming
     *
     * @param state
     *            --
     * @return --
     */
    public static boolean shouldApplyFarming(IBlockState state) {
        return isFullGrownBeetroot(state) || isFullGrownCactus(state) || isFullGrownSugarCane(state)
                || isFullGrownNetherWart(state) || isFullGrownCocoa(state) || isFullGrownBlockCrop(state)
                || isMelon(state) || isPumpkin(state);
    }

    static boolean isMelon(IBlockState state) {
        return state.getBlock() instanceof BlockMelon;
    }

    static boolean isPumpkin(IBlockState state) {
        return state.getBlock() instanceof BlockPumpkin;
    }

    static boolean isCactus(IBlockState state) {
        return state.getBlock() instanceof BlockCactus;
    }

    static boolean isFullGrownCactus(IBlockState state) {
        return isCactus(state) && ((Integer) state.getProperties().get(BlockCactus.AGE) > 7);
    }

    static boolean isBeetroot(IBlockState state) {
        return state.getBlock() instanceof BlockBeetroot;
    }

    static boolean isFullGrownBeetroot(IBlockState state) {
        return isBeetroot(state) && state.getProperties().get(BlockBeetroot.BEETROOT_AGE).equals(3);
    }

    static boolean isSugarCane(IBlockState state) {
        return state.getBlock() instanceof BlockReed;
    }

    static boolean isFullGrownSugarCane(IBlockState state) {
        return isSugarCane(state) && ((Integer) state.getProperties().get(BlockReed.AGE) > 7);
    }

    static boolean isNetherWart(IBlockState state) {
        return state.getBlock() instanceof BlockNetherWart;
    }

    static boolean isFullGrownNetherWart(IBlockState state) {
        return isNetherWart(state) && state.getProperties().get(BlockNetherWart.AGE).equals(3);
    }

    static boolean isCocoa(IBlockState state) {
        return state.getBlock() instanceof BlockCocoa;
    }

    static boolean isFullGrownCocoa(IBlockState state) {
        return isCocoa(state) && state.getProperties().get(BlockCocoa.AGE).equals(2);
    }

    static boolean isBlockCrop(IBlockState state) {
        return BlockUtils.isCrop(state) && !(state.getBlock() instanceof BlockBeetroot);
    }

    static boolean isFullGrownBlockCrop(IBlockState state) {
        return isBlockCrop(state) && state.getProperties().get(BlockCrops.AGE).equals(7);
    }
}
