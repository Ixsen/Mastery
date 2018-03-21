package mastery.experience.skillclasses;

public class FarmingMastery extends MasteryClasses {

    public FarmingMastery() {
        name = "Farming";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.FARMING;
    }
}
