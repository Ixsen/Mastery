package mastery.experience.skillclasses;

public class HusbandryMastery extends MasteryClass {

    public enum EXP_TYPE {
        ENTITY_TAMED(10), ANIMAL_HARVEST(4), ANIMAL_FED(1), ANIMAL_CHILD_SPAWN(1);

        public int value = 1;

        EXP_TYPE(int value) {
            this.value = value;
        }
    }

    public HusbandryMastery() {
        this.name = "Husbandry";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.HUSBANDRY;
    }

    public void increaseExperience(EXP_TYPE type) {
        this.increaseExperience(type.value);
    }
}
