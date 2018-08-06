package mastery;

import java.util.ArrayList;
import java.util.List;

import mastery.block.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MasteryBlocks {

    public static final List<Block> ALL_BLOCKS = new ArrayList<>();

    public static final Block RAINBOW_POWDER_BLOCK = new BlockBase("rainbow_powder_block", Material.GRASS,
	    MasteryMod.TAB_MASTERY, SoundType.SAND, 3F, 3F, "shovel", 0, 50F);
}
