package mastery.experience;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by Granis on 15/03/2018.
 */
public class MasteryPersistenceManager implements Capability.IStorage<IMastery> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IMastery> capability, IMastery iMastery, EnumFacing enumFacing) {
        return new NBTTagIntArray(iMastery.toIntArray());
    }

    @Override
    public void readNBT(Capability<IMastery> capability, IMastery iMastery, EnumFacing enumFacing, NBTBase nbtBase) {
        iMastery.readIntArray(((NBTTagIntArray) nbtBase).getIntArray());
    }
}
