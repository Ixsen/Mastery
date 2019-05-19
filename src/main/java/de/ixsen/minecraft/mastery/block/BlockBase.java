package de.ixsen.minecraft.mastery.block;

import de.ixsen.minecraft.mastery.MasteryBlocks;
import de.ixsen.minecraft.mastery.MasteryItems;
import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.common.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * Is the superclass for all custom blocks of the mastery mod. Makes life easier by providing a common naming structure for items and by
 * implmenting the {@linkplain IHasModel} interface.
 * 
 * @author Subaro
 */
public class BlockBase extends Block implements IHasModel {

    /**
     * Creates a new block with the given parameter in the {@linkplain CreativeTabs.BUILDING_BLOCKS} tab.
     */
    public BlockBase(String name, Material materialIn, SoundType type, float hardness, float resistance,
            String harvestTool, int harvestLevel) {
        this(name, materialIn, CreativeTabs.BUILDING_BLOCKS, type, hardness, resistance, harvestTool, harvestLevel, 0);
    }

    /**
     * Creates a new block with the given parameter in the {@linkplain CreativeTabs.BUILDING_BLOCKS} tab.
     */
    public BlockBase(String name, Material materialIn, SoundType type, float hardness, float resistance,
            String harvestTool, int harvestLevel, float lightLevel) {
        this(name, materialIn, CreativeTabs.BUILDING_BLOCKS, type, hardness, resistance, harvestTool, harvestLevel,
                lightLevel);
    }

    /**
     * Creates a new block with the given parameter.
     */
    public BlockBase(String name, Material materialIn, CreativeTabs tab, SoundType type, float hardness,
            float resistance, String harvestTool, int harvestLevel, float lightLevel) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(tab);
        this.setSoundType(type);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(harvestTool, harvestLevel);
        this.setLightLevel(lightLevel);
        MasteryBlocks.ALL_BLOCKS.add(this);
        MasteryItems.ALL_ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see mastery.common.util.IHasModel#registerModel()
     */
    @Override
    public void registerModel() {
        MasteryMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
