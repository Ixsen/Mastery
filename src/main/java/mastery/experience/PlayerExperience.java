package mastery.experience;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by Granis on 15/03/2018.
 */
public class PlayerExperience implements Capability.IStorage<Mastery> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<Mastery> capability, Mastery mastery, EnumFacing enumFacing) {
        return null;
    }

    @Override
    public void readNBT(Capability<Mastery> capability, Mastery mastery, EnumFacing enumFacing, NBTBase nbtBase) {
//        nbtBase.
    }
}
