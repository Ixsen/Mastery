package de.ixsen.minecraft.mastery.eventhandlers.mining;

import static de.ixsen.minecraft.mastery.eventhandlers.ExperienceDictionary.MINING_BLOCK;
import static de.ixsen.minecraft.mastery.eventhandlers.ExperienceDictionary.MINING_ORE;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

class MiningUtils {

    private static HashMap<Block, Integer> expMap = new HashMap<>();

    static {
        expMap.put(Blocks.COAL_ORE, 1);

        expMap.put(Blocks.IRON_ORE, 2);

        expMap.put(Blocks.REDSTONE_ORE, 3);
        expMap.put(Blocks.LIT_REDSTONE_ORE, 3);
        expMap.put(Blocks.LAPIS_ORE, 3);
        expMap.put(Blocks.QUARTZ_ORE, 3);

        expMap.put(Blocks.GOLD_ORE, 4);

        expMap.put(Blocks.DIAMOND_ORE, 5);
        expMap.put(Blocks.EMERALD_ORE, 5);

    }

    private static Block getBlockFromPosition(BlockPos pos, World world) {
        return world.getBlockState(pos).getBlock();
    }

    static int expAmountForBlock(Block block, boolean hasSilkTouch) {
        if (isOre(block, false) && !hasSilkTouch) {
            Integer expAmount = expMap.get(block) != null ? expMap.get(block)
                    : (1 + block.getHarvestLevel(block.getDefaultState()));
            return MINING_ORE.getValue() * expAmount;
        } else if (!(block instanceof IGrowable) && !(block instanceof IPlantable) && !(block == Blocks.AIR)
                && !(block == Blocks.BEDROCK)) {
            return MINING_BLOCK.getValue();
        }
        return 0;
    }

    static int expForExplosion(Explosion explosion, World world) {
        return explosion.getAffectedBlockPositions().stream()
                .mapToInt(blockPos -> expAmountForBlock(getBlockFromPosition(blockPos, world), false)).sum();
    }

    static boolean hasSilkTouchEnchantedTool(EntityPlayer player) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) > 0;
    }

    static boolean isOre(Block block, boolean ignoreBlockDrops) {
        return ignoreBlockDrops
                ? block != Blocks.GOLD_ORE && block != Blocks.IRON_ORE
                        && (block instanceof BlockOre || block == Blocks.REDSTONE_ORE)
                : block instanceof BlockOre || block == Blocks.REDSTONE_ORE;
    }
}
