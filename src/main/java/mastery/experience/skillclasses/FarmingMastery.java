package mastery.experience.skillclasses;

public class FarmingMastery extends MasteryClass {

    public FarmingMastery() {
        this.name = "Farming";
    }

    @Override
    public MASTERY_SPEC getSkillClass() {
        return MASTERY_SPEC.FARMING;
    }
}
