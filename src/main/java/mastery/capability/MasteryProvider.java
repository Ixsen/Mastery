package mastery.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Granis on 16/03/2018.
 */
public class MasteryProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IMastery.class)
    public static Capability<IMastery> MASTERY_CAPABILITY;

    private IMastery mastery = MASTERY_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing) {
        return capability == MASTERY_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing) {
        return capability == MASTERY_CAPABILITY ? MASTERY_CAPABILITY.<T>cast(this.mastery) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MASTERY_CAPABILITY.getStorage().writeNBT(MASTERY_CAPABILITY, this.mastery, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbtBase) {
        MASTERY_CAPABILITY.getStorage().readNBT(MASTERY_CAPABILITY, this.mastery, null, nbtBase);
    }
}
