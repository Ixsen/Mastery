package de.ixsen.minecraft.mastery.capability.player;

import java.util.Map;

import javax.annotation.Nullable;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by Granis on 15/03/2018.
 */
public class MasteryPersistenceManager implements Capability.IStorage<IMastery> {

    private static final String TAG_EXPERIENCE = "capability";
    private static final String TAG_LEVEL = "level";
    private static final String TAG_NAME = "name";

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IMastery> capability, IMastery iMastery, EnumFacing enumFacing) {
        return this.getNBTMasteryMap(iMastery);
    }

    @Override
    public void readNBT(Capability<IMastery> capability, IMastery iMastery, EnumFacing enumFacing, NBTBase nbtBase) {
        NBTTagCompound masteryMap = (NBTTagCompound) nbtBase;
        for (Map.Entry<MasterySpec, MasteryClass> entry : iMastery.getMasteries().entrySet()) {
            NBTTagCompound specificMasteryMap = masteryMap.getCompoundTag(entry.getValue().getName());
            entry.getValue().setLevel(specificMasteryMap.getInteger(TAG_LEVEL));
            entry.getValue().setExperience(specificMasteryMap.getInteger(TAG_EXPERIENCE));
            entry.getValue().setSpecifics(specificMasteryMap);
        }
    }

    private NBTTagCompound getNBTMasteryMap(IMastery mastery) {
        NBTTagCompound masteryMap = new NBTTagCompound();
        for (Map.Entry<MasterySpec, MasteryClass> entry : mastery.getMasteries().entrySet()) {
            masteryMap.setTag(entry.getValue().getName(), this.getNBTMap(entry.getValue()));
        }
        return masteryMap;
    }

    private NBTTagCompound getNBTMap(MasteryClass mastery) {
        NBTTagCompound specificMasteryMap = new NBTTagCompound();
        specificMasteryMap.setString(TAG_NAME, mastery.getName());
        specificMasteryMap.setInteger(TAG_LEVEL, mastery.getLevel());
        specificMasteryMap.setInteger(TAG_EXPERIENCE, mastery.getExperience());
        return mastery.getSpecifics(specificMasteryMap);
    }

}
