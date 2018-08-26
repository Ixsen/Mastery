package mastery.capability.world;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BlockInfoProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IBlockInfo.class)
    public static Capability<IBlockInfo> BLOCK_INFO_CAPABILITY;

    private IBlockInfo blockInfo = BLOCK_INFO_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == BLOCK_INFO_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == BLOCK_INFO_CAPABILITY ? BLOCK_INFO_CAPABILITY.<T>cast(this.blockInfo) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return BLOCK_INFO_CAPABILITY.getStorage().writeNBT(BLOCK_INFO_CAPABILITY, this.blockInfo, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        BLOCK_INFO_CAPABILITY.getStorage().readNBT(BLOCK_INFO_CAPABILITY, this.blockInfo, null, nbt);
    }
}
