package mastery.experience;

import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.experience.skillclasses.MasteryClasses;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by Granis on 15/03/2018.
 */
public class MasteryPersistenceManager implements Capability.IStorage<IMastery> {

    private static final String TAG_EXPERIENCE = "experience";
    private static final String TAG_LEVEL = "level";
    private static final String TAG_NAME = "name";


    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IMastery> capability, IMastery iMastery, EnumFacing enumFacing) {
        return getNBTMasteryMap(iMastery);
    }

    @Override
    public void readNBT(Capability<IMastery> capability, IMastery iMastery, EnumFacing enumFacing, NBTBase nbtBase) {
        NBTTagCompound nbtMap = (NBTTagCompound) nbtBase;
        for (Map.Entry<MASTERY_SPEC, MasteryClasses> entry : iMastery.getMasteries().entrySet()) {
            nbtMap.getCompoundTag(entry.getValue().getName());

        }


        iMastery.readIntArray(((NBTTagIntArray) nbtBase).getIntArray());
    }

    private NBTTagCompound getNBTMasteryMap(IMastery mastery) {
        NBTTagCompound nbtMap = new NBTTagCompound();
        for (Map.Entry<MASTERY_SPEC, MasteryClasses> entry : mastery.getMasteries().entrySet()) {
            nbtMap.setTag(entry.getValue().getName(), getNBTMap(entry.getValue()));
        }
        return null;
    }

    private NBTTagCompound getNBTMap(MasteryClasses mastery) {
        NBTTagCompound nbtMap = new NBTTagCompound();
        nbtMap.setString(TAG_NAME, mastery.getName());
        nbtMap.setInteger(TAG_LEVEL, mastery.getLevel());
        nbtMap.setInteger(TAG_EXPERIENCE, mastery.getExperience());
        return nbtMap;
    }

}
