package mastery.experience.skillclasses;

/**
 * Created by Granis on 18/03/2018.
 */
public abstract class MasteryClasses {

    private int experience = 0;
    protected String name = "mastery_placeholder";

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return experience / 10;
    }

    public void increaseExperience() {
        this.experience++;
    }

    public void increaseExperience(int amount) {
        this.experience += amount;
    }

    public abstract MASTERY_SPEC getSkillClass();

    @Override
    public String toString() {
        return name +" -> Level: " + getLevel() + " Exp: " + getExperience();
    }
}
