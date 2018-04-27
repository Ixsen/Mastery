package mastery.experience.skillclasses;

/**
 * Created by Granis on 18/03/2018.
 */
public abstract class MasteryClasses {

    private int experience = 0;
    private int level = 1;
    protected String name = "mastery_placeholder";


    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void increaseExperience() {
        this.experience++;
    }

    public void increaseExperience(int amount) {
        this.experience += amount;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getNextLevelExp() {
        return (getLevel() + 1) * 10;
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
}
