package mastery.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumBlockRenderType;

public class IngredientTable extends Block {

    public IngredientTable(String name, Material materialIn, CreativeTabs tab, float hardness, float resistance,
            String tool, int harvest) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setRegistryName("ingridienttable");
        this.setCreativeTab(tab);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(tool, harvest);
    }

    /**
     * render using a BakedModel (mbe01_block_simple.json --> mbe01_block_simple_model.json) not strictly required because the default (super
     * method) is MODEL.
     *
     * @param iBlockState
     *            --
     * @return --
     */
    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
        return EnumBlockRenderType.MODEL;
    }

}
