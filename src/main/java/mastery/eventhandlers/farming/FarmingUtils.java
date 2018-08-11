package mastery.eventhandlers.farming;

import mastery.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeetroot;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockMelon;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockReed;
import net.minecraft.block.state.IBlockState;

class FarmingUtils {

    private static boolean beetrootCheck(IBlockState state) {
        return state.getBlock() instanceof BlockBeetroot
                && state.getProperties().get(BlockBeetroot.BEETROOT_AGE).equals(3);
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
        return BlockUtils.isCrop(state) && !(block instanceof BlockBeetroot)
                && state.getProperties().get(BlockCrops.AGE).equals(7);
    }

    /**
     * Return whether or not the current harvest state should give capability for farming
     *
     * @param state
     *            --
     * @return --
     */
    static boolean shouldGetFarmingExp(IBlockState state) {
        return beetrootCheck(state) || cactusCheck(state) || sugarCaneCheck(state) || netherWartCheck(state)
                || cocoaCheck(state) || genericCheck(state) || state.getBlock() instanceof BlockMelon
                || state.getBlock() instanceof BlockPumpkin;
    }
}
