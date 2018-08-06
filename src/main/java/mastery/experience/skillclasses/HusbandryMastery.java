package mastery.experience.skillclasses;

public class HusbandryMastery extends MasteryClass {

    public HusbandryMastery() {
        this.name = "Husbandry";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.HUSBANDRY;
    }
}
