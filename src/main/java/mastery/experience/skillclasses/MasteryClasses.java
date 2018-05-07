package mastery.experience.skillclasses;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Granis on 18/03/2018.
 */
public abstract class MasteryClasses {

	private int experience = 0;
	private int level = 1;
	protected int nextLevelExperience = 10;
	protected String name = "mastery_placeholder";

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void increaseExperience() {
		increaseExperience(1);
	}

	public void increaseExperience(int amount) {
		this.experience += amount;
		if (experience >= nextLevelExperience) {
			level++;
			int nextLevel = nextLevelExperience;
			calcNextLevelExp();
			increaseExperience(-nextLevel);
		}
	}

	public void calcNextLevelExp() {
		nextLevelExperience = (int) (10.0f * Math.log(level + 1));
	}

	public void setLevel(int level) {
		this.level = level;
		calcNextLevelExp();
	}

	public int getLevel() {
		return level;
	}

	public int getNextLevelExp() {
		return nextLevelExperience;
	}

	public int getExperience() {
		return experience;
	}

	public String getName() {
		return name;
	}

	public abstract MASTERY_SPEC getSkillClass();

	@Override
	public String toString() {
		return name + " -> Level: " + getLevel() + " Exp: " + getExperience();
	}

	/**
	 * Overwrite this method if a mastery needs specifics saved. Only used for
	 * persistency
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
}
