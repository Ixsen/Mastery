package de.ixsen.minecraft.mastery.capability.player.skillclasses;

import de.ixsen.minecraft.mastery.MasteryMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Granis on 18/03/2018.
 */
public abstract class MasteryClass {

    private int experience = 0;
    private int level = 1;
    private int nextLevelExperience = 10;
    protected String name = "mastery_placeholder";
    private EntityPlayer player;

    public MasteryClass() {
        // Calculate the first next exp
        this.calcNextLevelExp();
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void increaseExperience() {
        this.increaseExperience(1);
    }

    public void increaseExperience(int amount) {
        this.experience += amount;
        if (this.experience >= this.nextLevelExperience) {
            MasteryMod.fireLevelUpEvent(this.getSkillClass(), this.player);
            this.level++;
            int nextLevel = this.nextLevelExperience;
            this.calcNextLevelExp();
            this.increaseExperience(-nextLevel);
        }
    }

    public void calcNextLevelExp() {
        this.nextLevelExperience = (int) (10.0f * Math.log(this.level + 1));
    }

    public void setLevel(int level) {
        this.level = level;
        this.calcNextLevelExp();
    }

    public int getLevel() {
        return this.level;
    }

    public int getNextLevelExp() {
        return this.nextLevelExperience;
    }

    public int getExperience() {
        return this.experience;
    }

    public String getName() {
        return this.name;
    }

    public abstract MasterySpec getSkillClass();

    @Override
    public String toString() {
        return String.format("%12s Level: %5d EXP: %7d", this.name, this.getLevel(), this.getExperience());
    }

    /**
     * Overwrite this method if a mastery needs specifics saved. Only used for persistency
     *
     * @param map
     *            Add specifics to this map
     * @return the map
     */
    public NBTTagCompound getSpecifics(NBTTagCompound map) {
        return map;
    }

    /**
     * Overwrite this method to load specific data previously saved via getSpecifics
     *
     * @param map
     *            read from this map
     */
    public void setSpecifics(NBTTagCompound map) {

    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    public void setPlayer(EntityPlayer player) {
        this.player = player;
    }
}
